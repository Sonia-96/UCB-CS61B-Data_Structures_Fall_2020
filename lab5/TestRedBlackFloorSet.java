import org.junit.Test;

import static edu.princeton.cs.algs4.StdRandom.uniform;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        AListFloorSet A = new AListFloorSet();
        RedBlackFloorSet R = new RedBlackFloorSet();
        for (int i = 0; i < 1000000; i++) {
            double randomNum = uniform(-5000, 5000);
            A.add(randomNum);
            R.add(randomNum);
        }

        for (int i = 0; i < 100000; i++) {
            double randomNum = uniform(-5000, 5000);
            assertEquals(A.floor(randomNum), R.floor(randomNum), 0.000001);
        }
    }
}
