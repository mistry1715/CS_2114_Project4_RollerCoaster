package rollercoaster;

import java.util.Arrays;

import queue.QueueInterface;

/**
 *  This data structure implements QueueInterface with 
 *  a circular array implementation. 
 *  It provides default queue behavior, 
 *  such as enqueue, dequeue, getFront, and isEmpty.
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.26
 * 
 * @param <T> Generic type
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    /**
     * An array of type T
     */
    private T[] queue;

    /**
     * Capacity of the queue
     */
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 100;

    /**
     * The push and pop index
     */
    private int enqueueIndex;
    private int dequeueIndex;

    /**
     * Size of the queue
     */
    private int size;

    /**
     * Constructor
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.enqueueIndex = 0;
        this.dequeueIndex = 0;
    }

    /**
     * Delete all entries in the queue
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        queue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.enqueueIndex = 0;
        this.dequeueIndex = 0;
    }

    /**
     * Pop a entry
     * 
     * @return Entry
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        else {
            T temp = queue[dequeueIndex];
            queue[dequeueIndex] = null;
            size--;
            incrementIndex(dequeueIndex);

            return temp;
        }
    }

    /**
     * Push an entry
     * 
     * @param entry The entry to be added
     */
    @Override
    public void enqueue(T entry) {
        if (size  == queue.length - 1) {
            ensureCapacity();
        }

        queue[enqueueIndex] = entry;
        size++;
        incrementIndex(enqueueIndex);
    }

    /**
     * Peek an entry
     * 
     * @return Entry
     */
    @Override
    public T getFront() {
        return queue[dequeueIndex];
    }

    /**
     * Check if the queue is empty
     * 
     * @return Return true if the queue is empty,
     *          else return false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Increase the index
     * 
     * @param index Index to be increased
     * @return Increased index
     */
    private int incrementIndex(int index) {
        return ((index + 1) % queue.length);
    }

    /**
     * Expand the capacity of the queue
     */
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

    /**
     * Get the size of the queue
     * 
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Transfer the queue to an array
     * 
     * @return Array
     */
    public Object[] toArray() {
        Object[] temp = new Object[size];
        int index = dequeueIndex;

        for (int i = 0; i < size; i++) {
            temp[i] = queue[index];
            incrementIndex(index);
        }

        return temp;
    }

    /**
     * Get the String of the queue
     * 
     * @return String
     */
    public String toString() {
        String temp = "";
        Object[] array = toArray();

        for (int i = 0; i < array.length; i++) {
            temp += array[i];
            if (i < array.length - 1) {
                temp += ",";
            }
        }

        return "[" + temp + "]";
    }


    /**
     * Check if this and other are equal
     * 
     * @return Return true if they are equal, 
     *          else return false
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        else if (this == other) {
            return true;
        }
        else if (this.getClass() != other.getClass()) {
            return false;
        }
        else {
            Object[] items = this.toArray(); 
            @SuppressWarnings("rawtypes")
            Object[] otherItems = ((ArrayQueue)other).toArray();
            ComparePersonHeight comparer = new ComparePersonHeight();
            Arrays.sort(items, comparer);
            Arrays.sort(otherItems, comparer);
            return Arrays.equals(items, otherItems);
        }
    }
}
