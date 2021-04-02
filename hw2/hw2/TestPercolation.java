package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    @Test
    public void testPercolation() {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(1, 1);
        p.open(2, 2);
        p.open(0, 2);
        p.open(1, 2);
        assertTrue(p.percolates());
    }

    @Test
    public void testPercolationStats() {
        int[] N = {10, 50, 100, 200, 500, 1000};
        for (int n: N) {
            PercolationStats p = new PercolationStats(n, 100, new PercolationFactory());
            double pMean = p.mean();
            double pStd = p.stddev();
            System.out.println("N = " + n + ", p* = " + pMean + ", std is " + pStd);
        }
    }
}
