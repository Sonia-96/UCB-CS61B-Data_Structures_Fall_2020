package es.datastructur.synthesizer;


import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class ArrayRingBufferIterator implements Iterator<T> {
        int index = first;
        @Override
        public boolean hasNext() {
            return index - first < fillCount;
        }

        @Override
        public T next() {
            index += 1;
            return rb[pos(index - 1)];
        }
    }

    public ArrayRingBufferIterator iterator() {
        return new ArrayRingBufferIterator();
    }

    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer<T> other = (ArrayRingBuffer) o;
        if (this.fillCount() != other.fillCount()) {
            return false;
        }
        ArrayRingBufferIterator otherIterator = other.iterator();
        for (T a : this) {
            if (otherIterator.hasNext()) {
                if (a != otherIterator.next()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = pos(last + 1);
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T toRemove = rb[first];
        rb[first] = null;
        first = pos(first + 1);
        fillCount -= 1;
        return toRemove;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first];
    }

    private int pos(int i) {
        return (i + rb.length) % rb.length;
    }
}
