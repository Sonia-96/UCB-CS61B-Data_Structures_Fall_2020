import org.junit.Test;
import static org.junit.Assert.*;

public class testUnionFind {
    @Test
    public void test() {
        UnionFind uf = new UnionFind(9);
        uf.connect(2, 3);
        uf.connect(1, 2);
        uf.connect(5, 7);
        uf.connect(8, 4);
        uf.connect(7, 2);
        assertEquals(3, uf.find(2));
        assertEquals(4, uf.find(8));
        uf.connect(0, 6);
        uf.connect(6, 4);
        uf.connect(6, 3);
        assertEquals(3, uf.find(8));
        assertEquals(3, uf.find(6));
        int[] expected = {6, 3, 3, -9, 3, 7, 3, 3, 3};
        assertArrayEquals(expected, uf.parent);
    }

    @Test
    public void test2() {
        UnionFind uf = new UnionFind(5); // 0 ~ 4
        int[] expected = {-1, -1, -1, -1, -1};
        assertArrayEquals(expected, uf.parent);
        uf.connect(1, 2);
        expected[1] = 2;
        expected[2] = -2;
        assertArrayEquals(expected, uf.parent);
        uf.connect(0, 3);
        expected[0] = 3;
        expected[3] = -2;
        assertArrayEquals(expected, uf.parent);
        assertFalse(uf.isConnected(1, 3));
        uf.connect(1, 4);
        expected[4] = 2;
        expected[2] = -3;
        assertArrayEquals(expected, uf.parent); //{2, 1, 4}, {3, 0}
        uf.connect(0, 1);
        expected[3] = 2;
        expected[2] = -5;
        assertTrue(uf.isConnected(1, 3));
        assertArrayEquals(expected, uf.parent);
        uf.find(0);
        expected[0] = 2;
        assertArrayEquals(expected, uf.parent);
    }
}
