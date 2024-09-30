# Huffman Coder
The main code of this repository is stored privately as per the University of Texas at Austin guidelines. If you would like to see it, please contact me and I can add you as a read-only collaborator on an archived private repository.

### Assignment 10 - Huffman Coding Analysis

NOTE: A small portion of the results from my experiments are at the bottom of this file.

Analysis: 
What kinds of file lead to lots of compressions? 
Based on the results from my experiments, larger files with many bytes are compressed more 
than smaller files with fewer bytes. (Reference revDictionary.txt for instance.)

What kind of files had little or no compression?
Based on the results from my experiments, smaller files with few bytes are compressed less 
than larger files with more bytes. (Reference majority of the files in the calgary directory
to the other 2 directories in general.)

What happens when you try and compress a huffman code file?
After completing a variety of further experiments, I concluded that a majority of compressed files 
I compressed for a second time had little to no compression. This is because depending on the 
headerFormat and other variables, unless the file is extremely large, there would be little to no 
compression.


### ******RESULTS******

compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\bib.hf
bib from         111261 to       73795 in        0.895
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\book1.hf
book1 from       768771 to       439409 in       3.965
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\book2.hf
book2 from       610856 to       369335 in       3.345
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\geo.hf
geo from         102400 to       73592 in        0.657
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\news.hf
news from        377109 to       247428 in       2.451
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\obj1.hf
obj1 from        21504 to        17085 in        0.166
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\obj2.hf
obj2 from        246814 to       195131 in       2.267
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\paper1.hf
paper1 from      53161 to        34371 in        0.349
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\paper2.hf
paper2 from      82199 to        48649 in        0.473
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\paper3.hf
paper3 from      46526 to        28309 in        0.365
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\paper4.hf
paper4 from      13286 to        8894 in         0.132
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\paper5.hf
paper5 from      11954 to        8465 in         0.129
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\paper6.hf
paper6 from      38105 to        25057 in        0.319
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\pic.hf
pic from         513216 to       107586 in       1.101
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\progc.hf
progc from       39611 to        26948 in        0.283
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\progl.hf
progl from       71646 to        44017 in        0.572
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\progp.hf
progp from       49379 to        31248 in        0.449
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\calgary\calgary\trans.hf
trans from       93695 to        66252 in        0.723

--------
total bytes read: 3251493
total compressed bytes 1845571
total percent compression 43.239
compression time: 18.641


compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\clegg.tif.hf
clegg.tif from   2149096 to      2034595 in      20.682
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\frymire.tif.hf
frymire.tif from         3706306 to      2188593 in      22.253
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\lena.tif.hf
lena.tif from    786568 to       766146 in       8.159
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\monarch.tif.hf
monarch.tif from         1179784 to      1109973 in      12.031
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\peppers.tif.hf
peppers.tif from         786568 to       756968 in       8.007
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\sail.tif.hf
sail.tif from    1179784 to      1085501 in      11.740
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\serrano.tif.hf
serrano.tif from         1498414 to      1127645 in      11.106
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\waterloo\waterloo\tulips.tif.hf
tulips.tif from  1179784 to      1135861 in      12.478

--------
total bytes read: 12466304
total compressed bytes 10205282
total percent compression 18.137
compression time: 106.456


compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\A7_Recursion.html.hf
A7_Recursion.html from   41163 to        26189 in        0.403
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\CiaFactBook2000.txt.hf
CiaFactBook2000.txt from         3497369 to      2260664 in      22.312
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\jnglb10.txt.hf
jnglb10.txt from         292059 to       168618 in       1.617
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\kjv10.txt.hf
kjv10.txt from   4345020 to      2489768 in      26.021
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\melville.txt.hf
melville.txt from        82140 to        47364 in        0.625
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\quotes.htm.hf
quotes.htm from  61563 to        38423 in        0.729
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\rawMovieGross.txt.hf
rawMovieGross.txt from   117272 to       53833 in        0.721
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\revDictionary.txt.hf
revDictionary.txt from   1130523 to      611618 in       6.405
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\syllabus.htm.hf
syllabus.htm from        33273 to        21342 in        0.254
compressing to: C:\Users\anika\Documents\College- Freshman Year\314 Data Structures\Assignment 10\BooksAndHTML\BooksAndHTML\ThroughTheLookingGlass.txt.hf
ThroughTheLookingGlass.txt from  188199 to       110293 in       1.302

--------
total bytes read: 9788581
total compressed bytes 5828112
total percent compression 40.460
compression time: 60.389
