package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import static org.junit.Assert.*;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufNoBottom; // avoid backwash
    private int N;
    private int[] isOpen;
    private int numOpenSites;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0!");
        }
        this.N = N;
        isOpen = new int[N * N + 2];
        numOpenSites = 0;
        // the index of top is 0, the index of bottom is N * N - 1
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufNoBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    public void open(int row, int col) {
        validate(row, col);
        int p = xyTo1D(row, col);
        if (isOpen[p] == 0) {
            isOpen[p] = 1;
            numOpenSites += 1;
            if (row == 0) {
                uf.union(0, p);
                ufNoBottom.union(0, p);
            } // 如果在第一排，则和顶部联通
            if (row == N - 1) {
                uf.union(N * N + 1, p);
            } // 如果在最后一排，则和底部联通

            int[][] neighbors = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] neighbor : neighbors) {
                int adjRow = row + neighbor[0];
                int adjCol = col + neighbor[1];
                if (adjRow >= 0 && adjRow < N && adjCol >= 0 && adjCol < N) {
                    if (isOpen(adjRow, adjCol)) {
                        uf.union(p, xyTo1D(adjRow, adjCol));
                        ufNoBottom.union(p, xyTo1D(adjRow, adjCol));
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isOpen[xyTo1D(row, col)] == 1;
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufNoBottom.connected(0, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return numOpenSites;
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
        assertEquals(5, P.numOpenSites);
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
