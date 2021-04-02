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
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0!");
        }
        this.N = N;
        open = new int[N * N + 2];
        openSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int row, int col) {
        validate(row, col);
        int p = xyTo1D(row, col);
        if (open[p] == 0) {
            open[p] = 1;
            openSites += 1;
            if (row == 0) {
                uf.union(0, p);
            } // 如果在第一排，则和顶部联通

            if (p - N > 0 && open[p - N] == 1) {
                uf.union(p, p - N);
            } // 上
            if (p + N <= N * N && open[p + N] == 1) {
                uf.union(p, p + N);
            } // 下
            if (p - 1 > 0 && open[p - 1] == 1) {
                uf.union(p, p - 1);
            } // 左
            if (p + 1 <= N * N && open[p + 1] == 1) {
                uf.union(p, p + 1);
            } // 右

            if (row == N - 1 && uf.connected(0, p)) {
                uf.union(N * N + 1, p);
            } // 如果在最后一排且和顶部联通，则和底部联通
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return open[xyTo1D(row, col)] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(0, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(0, N * N + 1);
    }

    private int xyTo1D(int row, int col) {
        return row * N + col + 1;
    }

    private void validate(int row, int col) {
        if (row < 0 || row > N - 1) {
            throw new IndexOutOfBoundsException("row " + row + " is not between 0 and " + (N - 1));
        }
        if (col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException("col " + col + " is not between 0 and " + (N - 1));
        }
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
        P.open(4, 2);
        assertFalse(P.isFull(4, 2));
    }
}
