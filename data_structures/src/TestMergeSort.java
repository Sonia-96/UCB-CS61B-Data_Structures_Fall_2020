import org.junit.Test;
import static org.junit.Assert.*;


public class TestMergeSort {
    MergeSort ms = new MergeSort();
    @Test
    public void test1() {
        int[] A = {5, 3, 5, 7, 1, 12, 0};
        int[] expected = {0, 1, 3, 5, 5, 7, 12};
        assertArrayEquals(expected, ms.mergeSort(A, 0, A.length - 1));
    }

    @Test
    public void test2() {
        int[] A = {};
        int[] expected = {};
        assertArrayEquals(expected, ms.mergeSort(A, 0, -1));
    }
}
