package hw2;

import edu.princeton.cs.introcs.Stopwatch;

public class RuntimeAnalysis {
    public static void main(String[] args) {
        int[] N = {10, 100, 500, 1000};
        int[] T = {10, 100};
        // test WeightedQuickUnion
        for (int n : N) {
            for (int t : T) {
                Stopwatch timer = new Stopwatch();
                new PercolationStats(n, t, new PercolationFactory());
                double time = timer.elapsedTime();
                System.out.println("N = " + n + ", T = " + t + ", time is " + time + " seconds");
            }
        }
    }
}
