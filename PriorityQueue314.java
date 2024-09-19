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

import java.util.LinkedList;

public class PriorityQueue314<E extends Comparable<? super E>> {
    
    //instance variables
    private LinkedList<E> con;

    public PriorityQueue314() {
        con = new LinkedList<E>();
    }

    public boolean enqueue(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("The data passed as an argument " +
                    "cannot be null.");
        }

        if (con.size() == 0) {
            con.add(elem);
        } else {
            boolean added = false;
            int k = 0;
            while (!added && k < con.size()) {
                if (elem.compareTo(con.get(k)) < 0) {
                    con.add(k, elem);
                    added = true;
                }
                k++;
            }
            if (!added) {
                con.add(elem);
            }
        }
        return true;
    }

    public E dequeue() {
        //queue must have at least 1 element
        if(con.size() == 0) {
            throw new IllegalStateException("Queue cannot be empty when calling dequeue");
        }
        return con.removeFirst();        
    }

    public int size() {
        return con.size();
    }

    
}
