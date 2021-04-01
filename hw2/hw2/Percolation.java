package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    WeightedQuickUnionUF uf;
    int N;
    int[] open;
    int openSites;

    public Percolation(int N) {
        this.N = N;
        open = new int[N * N + 2];
        openSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
        }
        for (int i = xyTo1D(N-1, 0); i <= N * N; i++) {
            uf.union(N * N + 1, i);
        }
    }

    public void open(int row, int col) {
        int p = xyTo1D(row, col);
        if (open[p] == 0) {
            open[p] = 1;
            openSites += 1;
            if (p - N > 0 && open[p - N] == 1) {
                uf.union(p, p - N);
            }
            if (p + N <= N * N && open[p + N] == 1) {
                uf.union(p, p + N);
            }
            if (p - 1 > 0 && open[p - 1] == 1) {
                uf.union(p, p - 1);
            }
            if (p + 1 <= N * N && open[p + 1] == 1) {
                uf.union(p, p + 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return open[xyTo1D(row, col)] == 1;
    }

    public boolean isFull(int row, int col) {
        return uf.connected(0, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(0, N * N + 1);
    }

    private int xyTo1D(int r, int c) {
        return r * N + c + 1;
    }

    @Test
    public static void main(String[] args) {
        Percolation P = new Percolation(5);
        P.open(3, 4);
        P.open(2, 4);
        P.open(2, 2);
        P.open(2, 3);
        P.open(0, 2);
        assertEquals(5, P.openSites);
        assertTrue(P.isFull(0, 2));
        assertFalse(P.isFull(2, 2));
        P.open(1, 2);
        assertTrue(P.isFull(2, 2));
        assertFalse(P.percolates());
        P.open(4, 4);
        assertTrue(P.percolates());
    }

}
