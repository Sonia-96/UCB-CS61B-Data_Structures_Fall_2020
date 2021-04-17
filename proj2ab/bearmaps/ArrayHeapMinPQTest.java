package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testSimple() {
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(5, 5);
        minPQ.add(3, 3);
        minPQ.add(8, 8);
        minPQ.add(1, 1);
        minPQ.add(6, 6);
        minPQ.add(0, 0);
        assertEquals(6, minPQ.size());
        assertEquals(0, minPQ.getSmallest(), 0.000001);
        minPQ.removeSmallest();
        assertEquals(5, minPQ.size());
        assertEquals(1, minPQ.getSmallest(), 0.000001);
        minPQ.changePriority(8, 0);
        assertEquals(8, minPQ.getSmallest(), 0.000001);
    }

    @Test
    public void testRandomly() {
        ArrayHeapMinPQ<Double> minPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Double> naiveMinPQ = new NaiveMinPQ<>();
        for (int i = 0; i < 5000; i++) {
            double x = StdRandom.uniform(-1000, 1000);
            if (!minPQ.contains(x)) {
                minPQ.add(x, x);
                naiveMinPQ.add(x, x);
            }
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(naiveMinPQ.getSmallest(), minPQ.getSmallest());
            minPQ.removeSmallest();
            naiveMinPQ.removeSmallest();
        }
    }

    @Test
    public void testTiming() {
        ArrayHeapMinPQ<Double> betterMinPQ = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Double> naiveMinPQ = new NaiveMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            double x = StdRandom.uniform(-100000, 100000);
            if (!betterMinPQ.contains(x)) {
                betterMinPQ.add(x, x);
                naiveMinPQ.add(x, x);
            }
        }

        int size = betterMinPQ.size();

        Stopwatch betterSw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            betterMinPQ.getSmallest();
            betterMinPQ.removeSmallest();
        }
        double betterTime = betterSw.elapsedTime();
        System.out.println("ArrayHeapMinPQ 10000 queries on " + size + " points: " + betterTime + "seconds");

        Stopwatch naiveSw = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.getSmallest();
            naiveMinPQ.removeSmallest();
        }
        double naiveTime = naiveSw.elapsedTime();
        System.out.println("NaiveMinPQ 10000 queries on " + size + " points: " + naiveTime + "seconds");
    }
}
