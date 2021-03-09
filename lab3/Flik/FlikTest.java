import org.junit.Test;

public class FlikTest {
    @Test
    public void testFlick() {
        int i = 0;
        for (int j = 0; j < 500; i++, j++) {
            if (!Flik.isSameNumber(i, j)) {
                // print addresses of the numbers
                System.out.println(i + ": " + System.identityHashCode(i) + " "
                        + j + ": " + System.identityHashCode(j));
            }
        }
    }
}
