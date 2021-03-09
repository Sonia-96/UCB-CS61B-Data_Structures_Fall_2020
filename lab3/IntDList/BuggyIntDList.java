/**
 * @author Vivant Sakore on 1/29/2020
 */
public class BuggyIntDList extends IntDList {

    /**
     * @param values creates a BuggyIntDList with ints values.
     */
    public BuggyIntDList(Integer... values) {
        super(values);
    }


    /**
     * Merge IntDList `l` into the calling IntDList
     * Assume that the two IntDLists being merged are sorted individually before merge.
     * The resulting IntDList must also be sorted in ascending order.
     *
     * @param l Sorted IntDList to merge
     */
    public void mergeIntDList(IntDList l) {
        front = sortedMerge(this.front, l.front);
        back = front;
        while (back.next != null) {
            back = back.next;
        }
    }

    /**
     * Recursively merge nodes after value comparison
     *
     * @param d1 Node 1
     * @param d2 Node 2
     * @return Nodes arranged in ascending sorted order
     */
    private DNode sortedMerge(DNode d1, DNode d2) {

        // ------ WRITE ADDITIONAL CODE HERE AND ONLY HERE (IF NEEDED) ------
        if (d1 == null) {
            return d2;
        }
        if (d2 == null) {
            return d1;
        }
        // ------------------------------------------------------------------

        if (d1.val <= d2.val) {
            d1.next = sortedMerge(d1.next, d2);
            d1.next.prev = d1;
            d1.prev = null;
            return d1;
        } else {
            d2.next = sortedMerge(d1, d2.next);
            d2.next.prev = d2;
            d2.prev = null;
            return d2;
        }
    }


    /**
     * Reverses IntDList in-place (destructive). Does not create a new IntDList.
     */
    public void reverse() {

        DNode temp = null;
        DNode p = front;

        // HINT: What does this while loop do? Use Debugger and Java Visualizer to figure out.
        while (p != null) {
            temp = p.prev;
            p.prev = p.next;
            p.next = temp;
            p = p.prev;

        // HINT: What does this if block do? Use Debugger and Java Visualizer to figure out.
        if (temp != null) {
            // ------ WRITE ADDITIONAL CODE HERE AND ONLY HERE (IF NEEDED) -----
            back = front;
            // -----------------------------------------------------------------
            front = temp.prev;
        }
    }
}
