package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test ArrayQueue class
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.26
 */
public class ArrayQueueTest {

    /**
     * ArrayQueue for the test
     */
    ArrayQueue<String> queue;

    /**
     * ArrayQueue for the test
     */
    ArrayQueue<String> queue2;

    /**
     * ArrayQueue for the test
     */
    ArrayQueue<String> queue3;

    /**
     * ArrayQueue for the test
     */
    ArrayQueue<String> queueNull;

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        queue = new ArrayQueue<String>();
        queue2 = new ArrayQueue<String>();
        queue3 = new ArrayQueue<String>();

        queue.enqueue("1");
        queue2.enqueue("2");
        queue3.enqueue("1");     
    }

    /**
     * Test clear
     */
    @Test
    public void testClear() {
        queue.clear();
        assertEquals(queue.size(), 0);
    }

    /**
     * Test dequeue
     */
    @Test
    public void testDequeue() {
        Exception ex = null;

        assertEquals(queue.dequeue(), "1");

        try {
            queue.dequeue();
            fail("dequeue() is not throwing an exception when it should");
        }
        catch (EmptyQueueException e) {
            ex = e;
        }

        assertNotNull(ex);
        assertTrue("dequeue() is throwing the wrong type of exceptions",
                ex instanceof EmptyQueueException);
    }

    /**
     * Test enqueue
     */
    @Test
    public void testEnqueue() {
        Exception ex = null;

        for (int i = 0; i < 99; i++) {
            queue.enqueue("" + i);
            assertEquals(queue.size(), i + 2);
        }

        try {
            queue.enqueue("101");
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
    }

    /**
     * Test getFront
     */
    @Test
    public void testGetFront() {
        assertEquals(queue.getFront(), "1");
    }

    /**
     * Test isEmpty
     */
    @Test
    public void testIsEmpty() {
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    /**
     * Test size
     */
    @Test
    public void testSize() {
        assertEquals(queue.size(), 1);
    }

    /**
     * Test toArray
     */
    @Test
    public void testToArray() {
        Object[] temp = queue.toArray();
        assertEquals(temp[0], "1");
    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        queue.enqueue("2");
        assertEquals(queue.toString(), "[1, 2]");
    }

    /**
     * Test equals
     */
    @Test
    public void testEquals() {
        assertFalse(queue.equals(queueNull));
        assertTrue(queue.equals(queue));
        assertFalse(queue.equals("1"));
        assertFalse(queue.equals(queue2));
        assertTrue(queue.equals(queue3));
    }

}
