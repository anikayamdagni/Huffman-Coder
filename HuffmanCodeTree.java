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
import java.util.Map;
import java.util.TreeMap;

public class HuffmanCodeTree {

    //instance variables
    private TreeNode root;
    private int numOfLeafNodes;
    private int numOfInternalNodes;
    
    public HuffmanCodeTree(PriorityQueue314<TreeNode> pq) {
        //size of priority queue is number of leaf nodes
        numOfLeafNodes = pq.size();
        TreeNode n = null;
        while (pq.size() > 1) {
            //add an internal node each time loop runs
            numOfInternalNodes++;

            n = new TreeNode(pq.dequeue(), -1, pq.dequeue());
            pq.enqueue(n);            
        }
        
        root = n;
    }

    public void setRoot(TreeNode n) {
        root = n;
    }

    public int getNumOfLeafNodes() {
        return numOfLeafNodes;
    }

    public int size() {
        return numOfInternalNodes + numOfLeafNodes;
    }

    public Map<Integer, String> getNewCodes() {
        return getNewCodesHelp(root, "", new TreeMap<Integer, String>());
    }

    private Map<Integer, String> getNewCodesHelp(TreeNode n, String path, Map<Integer, String> temp) {
        if (n != null) {
            getNewCodesHelp(n.getLeft(), path + "0", temp);
            if (n.isLeaf()) {
                temp.put(n.getValue(), path);
            }
            getNewCodesHelp(n.getRight(), path + "1", temp);
        }
        return temp;
    } 

    public String preOrderTree() {
        return preOrderTreeHelp(root, new StringBuilder());
    }

    /* 
     * Pre order from class
     */
    private String preOrderTreeHelp(TreeNode n, StringBuilder order) {
        if (n != null) {
            if (n.isLeaf()) {
                order.append("1");
            } else {
                order.append("0");
            }
            preOrderTreeHelp(n.getLeft(), order.append("0"));
            preOrderTreeHelp(n.getRight(), order.append("1"));
        }
        return order.toString();
    } 

    public int decode(HuffmanCodeTree h, BitInputStream bin, BitOutputStream bout) throws IOException {
        int count = 0;
        //get ready to walk tree, start at root
        StringBuilder sb = new StringBuilder();
        TreeNode n = h.root;
        boolean done = false;
        while(!done) {
            int bit = bin.readBits(1);
            if(bit == -1) {
                throw new IOException("Error reading compressed file. \n" +
                    "unexpected end of input. No PSEUDO_EOF value.");
            } else {
                //move left or right in tree based on value of bit
                if (bit == 0) {
                    n = n.getLeft();
                } else {
                    n = n.getRight();
                }
                if(n.isLeaf()) { 
                    if(n.getValue() == IHuffConstants.PSEUDO_EOF) {
                        done = true; //check if reached peof so we can stop
                    } else { 
                        bout.writeBits(IHuffConstants.BITS_PER_WORD, n.getValue());
                        count += IHuffConstants.BITS_PER_WORD;
                        n = h.root; //reset the node back to the root
                    }
                }
            }  
        }
        return count;        
    } 
    

}
