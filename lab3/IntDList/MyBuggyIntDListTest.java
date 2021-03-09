import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyBuggyIntDListTest {

    private BuggyIntDList l, m;

    @Test
    public void testMergeIntDList() {
        /**
         * I found a new bug in l.MergeIntDList(m):
         * if the back of merged list is in m,
         * l.getBack() will meet NullPointerException or a wrong answer.
         * the solution is find the last node of merged list
         * then assign it to `back`.
         */
        l = new BuggyIntDList();
        m = new BuggyIntDList(3, 5);
        l.mergeIntDList(m);
        assertEquals("Size after merge should be 2", 2, l.size());
        assertEquals(".getFront() should be 3", 3, l.getFront());
        assertEquals(".getBack() should be 5", 5, l.getBack());
        assertEquals("First item should be 3", 3, l.get(0));
        assertEquals("Second item should be 5", 5, l.get(1));
        assertEquals("Last item should be 5", 5, l.get(-1));

        l = new BuggyIntDList(1);
        m = new BuggyIntDList(3, 5);
        l.mergeIntDList(m);
        assertEquals("Size after merge should be 3", 3, l.size());
        assertEquals(".getFront() should be 1", 1, l.getFront());
        assertEquals(".getBack() should be 5", 5, l.getBack());
        assertEquals("First item should be 1", 1, l.get(0));
        assertEquals("Second item should be 3", 3, l.get(1));
        assertEquals("Third item should be 5", 5, l.get(2));
        assertEquals("Last item should be 5", 5, l.get(-1));
    }

    @Test
    public void testReverse() {
        l = new BuggyIntDList(1, 15, 23, 37, 90, 101, 105, 107);
        l.reverse();
        assertEquals("Size after reversal should be 8", 8, l.size());
        assertEquals(".getFront() after reversal should be 107", 107, l.getFront());
        assertEquals(".getBack() after reversal should be 1", 1, l.getBack());
        assertEquals("First item after reversal should be 107", 107, l.get(0));
        assertEquals("Second item after reversal should be 105", 105, l.get(1));
        assertEquals("Third item after reversal should be 101", 101, l.get(2));
        assertEquals("Fourth item after reversal should be 90", 90, l.get(3));
        assertEquals("Fifth item after reversal should be 37", 37, l.get(4));
        assertEquals("Sixth item after reversal should be 23", 23, l.get(5));
        assertEquals("Seventh item after reversal should be 15", 15, l.get(6));
        assertEquals("Eighth item after reversal should be 1", 1, l.get(7));
    }
}
