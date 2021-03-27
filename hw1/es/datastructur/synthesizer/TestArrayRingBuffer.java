package es.datastructur.synthesizer;
import org.junit.Test;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(3.1);
        arb.enqueue(5.0);
        arb.enqueue(2.2);
        assertTrue(arb.isFull());
        assertEquals(3, arb.fillCount());
//        arb.enqueue(6.0);
        assertEquals(3.1, arb.peek(), 0.0001);
        assertEquals(3.1, (double) arb.dequeue(), 0.0001);
        assertEquals(5.0, arb.peek(), 0.0001);
        assertEquals(5.0, (double) arb.dequeue(), 0.0001);
        assertEquals(2.2, arb.dequeue(), 0.0001);
        System.out.println(Math.round(2.6));
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> A = new ArrayRingBuffer<>(5);
        ArrayRingBuffer<Integer> B = new ArrayRingBuffer<>(5);
        for (int i = 0; i < 5; i++) {
            A.enqueue(i);
            B.enqueue(i);
        }
        assertTrue(A.equals(B));
        A.dequeue();
        A.enqueue(1);
        assertFalse(A.equals(B));
    }
}
