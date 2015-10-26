package rollercoaster;

import queue.QueueInterface;

public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.enqueueIndex = 0;
        this.dequeueIndex = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.enqueueIndex = 0;
        this.dequeueIndex = 0;
    }

    @Override
    public T dequeue() {
        T temp = queue[dequeueIndex];
        queue[dequeueIndex] = null;
        size--;
        incrementIndex(dequeueIndex);
        
        return temp;
    }

    @Override
    public void enqueue(T entry) {
        if (size  == queue.length - 1) {
            ensureCapacity();
        }

        queue[enqueueIndex] = entry;
        size++;
        incrementIndex(enqueueIndex);
    }

    @Override
    public T getFront() {
        return queue[dequeueIndex];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }

    private void ensureCapacity() {
        if (queue.length > MAX_CAPACITY) {
            throw new IllegalStateException();
        }
        else {
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Object[queue.length + 10];

            for (int i = 0; i < queue.length - 1; i++) {
                temp[i] = queue[i];
            }

            queue = temp;
        }
    }
}
