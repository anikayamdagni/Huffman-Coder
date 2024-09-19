/*  Student information for assignment:
 *
 *  On my honor, Anika Yamdagni, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Number of slip days used: 0
 *
 *  Student 1 (Student whose Canvas account is being used)
 *  UTEID: ay7493
 *  email address: anika.yamdagni@utexas.edu
 *  Grader name: David Klinger
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TreeMap;
import java.util.Map;

public class SimpleHuffProcessor implements IHuffProcessor {

    private IHuffViewer myViewer;
    private int originalSize;
    private int compressedSize;
    private int headFormat;
    private Map<Integer, Integer> freq;
    private HuffmanCodeTree hct;
    private Map<Integer, String> newCodes;

    /**
     * Preprocess data so that compression is possible ---
     * count characters/create tree/store state so that
     * a subsequent call to compress will work. The InputStream
     * is <em>not</em> a BitInputStream, so wrap it int one as needed.
     * @param in is the stream which could be subsequently compressed
     * @param headerFormat a constant from IHuffProcessor that determines what kind of
     * header to use, standard count format, standard tree format, or
     * possibly some format added in the future.
     * @return number of bits saved by compression or some other measure
     * Note, to determine the number of
     * bits saved, the number of bits written includes
     * ALL bits that will be written including the
     * magic number, the header format number, the header to
     * reproduce the tree, AND the actual data.
     * @throws IOException if an error occurs while reading from the input file.
     */
    public int preprocessCompress(InputStream in, int headerFormat) throws IOException {
        originalSize = 0;
        compressedSize = 0;
        headFormat = headerFormat;
        BitInputStream bin = new BitInputStream(in);

        //Step 1: Store frequencies of all values in map
        freq = new TreeMap<>();
        int ch;
        while ((ch = bin.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
            //add number of bits in original file to original size 
            originalSize += IHuffConstants.BITS_PER_WORD;
            if (freq.containsKey(ch)) {
                freq.put(ch, freq.get(ch) + 1); //increment frequency if in map
            } else {
                freq.put(ch, 1); //set frequency to 1 if not in map
            }
        }

        //Step 2: Add values to priority queue based on frequency
        PriorityQueue314<TreeNode> pq = new PriorityQueue314<>();
        for (int c : freq.keySet()) {
            TreeNode n = new TreeNode(c, freq.get(c));
            pq.enqueue(n);
        }

        //Step 3: Add PEOF to frequency and priority queue
        TreeNode n = new TreeNode(IHuffConstants.PSEUDO_EOF, 1);
        pq.enqueue(n);

        //Step 4: Build Huffman Code Tree based on prioritized values
        hct = new HuffmanCodeTree(pq);

        //Step 5: Perform a traversal of tree to get new codes for values
        newCodes = hct.getNewCodes();
        
        //Step 6: Add number of bits in magic number and header constant to compressedSize
        compressedSize += IHuffConstants.BITS_PER_INT * 2;

        //Step 7: Add number of bits in header content for either SCF/STF
        if (headerFormat == IHuffProcessor.STORE_COUNTS) {
            compressedSize += IHuffConstants.BITS_PER_INT * IHuffConstants.ALPH_SIZE;
        } else if (headerFormat == IHuffProcessor.STORE_TREE) {
            compressedSize += IHuffConstants.BITS_PER_INT;
            compressedSize += hct.size();
            compressedSize += hct.getNumOfLeafNodes() * (IHuffConstants.BITS_PER_WORD + 1);
        }

        //Step 8: Add number of bits in compressed data
        for (int c: freq.keySet()) {
        	// multiply the frequency of each number times the length of its code
        	String binaryString = newCodes.get(c);
        	compressedSize += freq.get(c) * binaryString.length();
        }

        //Step 9: Add number of bits in PEOF
        String peof = newCodes.get(IHuffConstants.PSEUDO_EOF);
        compressedSize += peof.length();

        myViewer.showMessage("preprocess completed " + (originalSize - compressedSize));

        bin.close();
        return originalSize - compressedSize;
    }

    /**
	 * Compresses input to output, where the same InputStream has
     * previously been pre-processed via <code>preprocessCompress</code>
     * storing state used by this call.
     * <br> pre: <code>preprocessCompress</code> must be called before this method
     * @param in is the stream being compressed (NOT a BitInputStream)
     * @param out is bound to a file/stream to which bits are written
     * for the compressed file (not a BitOutputStream)
     * @param force if this is true create the output file even if it is larger than the input file.
     * If this is false do not create the output file if it is larger than the input file.
     * @return the number of bits written.
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        //Check if file should be compressed according to file sizes and force
        if (!force && compressedSize > originalSize) {
            myViewer.showError("Compressed file has " + (compressedSize - originalSize) + 
                    " more bits than uncompressed file. \n" + 
                    "Select \"force compression\" option to compress.");
        }
        
        BitInputStream bin = new BitInputStream(in);
        BitOutputStream bout = new BitOutputStream(out);

        //Step 1: Add the magic number to the new compressed file
        bout.writeBits(IHuffConstants.BITS_PER_INT, IHuffConstants.MAGIC_NUMBER);

        //Step 2 & 3: Add the header format constant and header content for SCF/STF
        if (headFormat == IHuffProcessor.STORE_COUNTS) {
            //SCF header constant
            bout.writeBits(IHuffConstants.BITS_PER_INT, IHuffConstants.STORE_COUNTS);
            for(int k = 0; k < IHuffConstants.ALPH_SIZE; k++) { //SCF header content
                if (freq.containsKey(k)) {
                    bout.writeBits(IHuffConstants.BITS_PER_INT, freq.get(k));
                } else {
                    bout.writeBits(IHuffConstants.BITS_PER_INT, 0);
                }
            }
        } else if (headFormat == IHuffProcessor.STORE_TREE) {
            //STF header constant
            bout.writeBits(IHuffConstants.BITS_PER_INT, IHuffConstants.STORE_TREE);
            //STF header content
            bout.writeBits(IHuffConstants.BITS_PER_INT, hct.size());
            String str = hct.preOrderTree();
            for (int k = 0; k < str.length(); k++) {
                bout.writeBits(1, Integer.parseInt(str.substring(k, k + 1)));
            }
        }

        //Step 4: Add the compressed data using the new Huffman codes
        bin.close();
        bin = new BitInputStream(in);
        int ch;
        while ((ch = bin.readBits(IHuffConstants.BITS_PER_WORD)) != -1) {
            for (int k = 0; k < newCodes.get(ch).length(); k++) {
                bout.writeBits(1, 
                        Integer.parseInt(newCodes.get(ch).substring(k, k + 1)));
            }
        }

        //Step 5: Add the PEOF
        String peof = newCodes.get(IHuffConstants.PSEUDO_EOF);
        for (int k = 0; k < peof.length(); k++) {
            bout.writeBits(1, Integer.parseInt(peof.substring(k, k + 1)));
        }

        //Step 6: Add the Extra Padding of 0's
        bout.flush();

        bin.close();
        bout.close();

        myViewer.showMessage("compression completed " + compressedSize);

        return compressedSize;        
    }

    /**
     * Uncompress a previously compressed stream in, writing the
     * uncompressed bits/data to out.
     * @param in is the previously compressed data (not a BitInputStream)
     * @param out is the uncompressed file/stream
     * @return the number of bits written to the uncompressed file/stream
     * @throws IOException if an error occurs while reading from the input file or
     * writing to the output file.
     */
    public int uncompress(InputStream in, OutputStream out) throws IOException {
	    int count = 0; //stores the number of bits written to the uncompressed file/stream
        BitInputStream bin = new BitInputStream(in);
        BitOutputStream bout = new BitOutputStream(out);

        //Step 1: Checks if it has magic number
        if (hasMagic(bin)) {
            bin.close();
            bout.close();
            return -1;
        }

        //Step 2 & 3: Determine header format (SCF/STF) & store header content in HuffmanCodeTree
        int headerFormat = bin.readBits(IHuffConstants.BITS_PER_INT);
        HuffmanCodeTree h = new HuffmanCodeTree(new PriorityQueue314<TreeNode>());
        if (headerFormat == IHuffProcessor.STORE_COUNTS) { //SCF header
            PriorityQueue314<TreeNode> pq = new PriorityQueue314<>(); //make priority queue
            for(int k = 0; k < IHuffConstants.ALPH_SIZE; k++) { 
                int frequeny = bin.readBits(IHuffConstants.BITS_PER_INT);
                if (frequeny != 0) {    //add to priority queue if its frequency is not 0
                    TreeNode n = new TreeNode(k, frequeny);
                    pq.enqueue(n);
                }
            }
            //add peof to priority queue
            pq.enqueue(new TreeNode(IHuffConstants.PSEUDO_EOF, 1));
            //store data in HuffmanCodeTree
            h = new HuffmanCodeTree(pq);
        } else if (headerFormat == IHuffProcessor.STORE_TREE) {
            bin.readBits(IHuffConstants.BITS_PER_INT);
            //store data in HuffmanCodeTree
            h.setRoot(readStoreTreeHeader(bin));
        }
        
        //Step 4: Write all the uncompressed data to the new uncompressed file
        count = h.decode(h, bin, bout);

        bin.close();
        bout.close();

        myViewer.showMessage("uncompression completed " + count);

        return count;
    }
    
    /*
     * Stores the STF header information provided in bin in a HuffmanCodeTree
     */
    private TreeNode readStoreTreeHeader(BitInputStream bin) throws IOException {
        int bit = bin.readBits(1);
        if (bit == 0) { //internal node
            TreeNode newNode = new TreeNode(readStoreTreeHeader(bin), 
                    IHuffConstants.ALPH_SIZE, readStoreTreeHeader(bin));
            return newNode;
        } else if (bit == 1) { //leaf node
            TreeNode newNode = new TreeNode(bin.readBits(IHuffConstants.BITS_PER_WORD + 1), 0);
            return newNode;
        } else { //ran out of bits while making tree - somthing is wrong in input file
            throw new IOException("Ran out of bits while trying to form our Huffman Code Tree." +
                    "This means something is incorrect about the format of the input file.");
        }
    }        

    /*
     * Checks if the file starts with magic number
     */
    private boolean hasMagic(BitInputStream b) throws IOException {
        int magic = b.readBits(IHuffConstants.BITS_PER_INT);
        if (magic != IHuffConstants.MAGIC_NUMBER) {
            myViewer.showError("Error reading compressed file. \n" +
                    "File did not start with the huff magic number.");
            return true;
        }
        return false;
    }

    public void setViewer(IHuffViewer viewer) {
        myViewer = viewer;
    }

    private void showString(String s){
        if (myViewer != null) {
            myViewer.update(s);
        }
    }
}
