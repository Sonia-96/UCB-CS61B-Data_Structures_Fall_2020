package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import static java.lang.Math.sqrt;

public class PercolationStats {
    double[] openFraction;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        openFraction = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation P = pf.make(N);
            while (!P.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                P.open(row, col);
            }
            double temp = P.numberOfOpenSites() * 1.0 /  (N * N);
            openFraction[i] = temp;
        }
    }

    public double mean() {
        return StdStats.mean(openFraction);
    }

    public double stddev() {
        return StdStats.stddev(openFraction);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / sqrt(openFraction.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / sqrt(openFraction.length);
    }

}
