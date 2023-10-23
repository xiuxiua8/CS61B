package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        this.capacity = capacity;

    }
    private void indexcheck() {
        if (first >= capacity) {
            first -= capacity;
        }
        if (last >= capacity) {
            last -= capacity;
            throw new RuntimeException("Ring buffer overflow");

        }
        if (fillCount > capacity) {
            fillCount -= 1;
        }
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        try {
            rb[last] = x;
            last += 1;
            fillCount += 1;
            indexcheck();
        } catch (RuntimeException e) {
            System.out.println("Tried to run: " + e);
        }
    }

    /**
     * Dequeue the oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        T returnfirst = rb[first];
        try {
            if (this.isEmpty()) {
                throw new RuntimeException("Ring buffer underflow");
            }
            first += 1;
            fillCount -= 1;
            indexcheck();
            return returnfirst;
        } catch (RuntimeException e) {
            System.out.println("Tried to run: " + e);
            return null;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        try {
            if (this.isEmpty()) {
                throw new RuntimeException("Ring buffer is empty");
            }
            return rb[first];
        } catch (RuntimeException e) {
            System.out.println("Tried to run: " + e);
            return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

    public class BufferIterator implements Iterator<T> {
        private int ptr;
        public BufferIterator() {
            ptr = 0;
        }
        public boolean hasNext() {
            return (ptr != capacity);
        }
        public T next() {
            T returnVal = rb[ptr];
            ptr += 1;
            return returnVal;
        }
    }

}
