package rollercoaster;

import static org.junit.Assert.*;

import java.util.Arrays;

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
    ArrayQueue<String> array;

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        array = new ArrayQueue<String>();   
        array.enqueue("0");
    }

    /**
     * Test clear
     */
    @Test
    public void testClear() {
        array.clear();
        assertEquals(0, array.size());

        for (int i = 0; i < 5; i++) {
            array.enqueue(i + "");
        }

        array.clear();
        assertEquals(0, array.size());
    }

    /**
     * Test dequeue
     */
    @Test
    public void testDequeue() {
        array.enqueue("1");
        array.enqueue("2");

        assertEquals("0", array.dequeue());
        assertEquals("1", array.dequeue());
        assertEquals("2", array.dequeue());

        Exception ex = null;

        try {
            array.dequeue();
            fail("dequeue() is not throwing an exception when it should");
        }
        catch (Exception e) {
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
        array.enqueue("1");
        assertEquals(2, array.size());

        for (int i = 0; i < 9; i++) {
            array.enqueue("" + i);
        }

        assertEquals(11, array.size());

        for (int i = 0; i < 89; i++) {
            array.enqueue("" + i);
        }

        Exception ex = null;

        try {
            array.enqueue("1");
        }
        catch (Exception e) {
            ex = e;
        }

        assertTrue("enqueue() is throwing the wrong type of exceptions",
                ex instanceof IllegalStateException);
    }

    /**
     * Test getFront
     */
    @Test
    public void testGetFront() {
        assertEquals("0", array.getFront());
        array.dequeue();
        assertNull(array.getFront());    
    }

    /**
     * Test isEmpty
     */
    @Test
    public void testIsEmpty() {
        assertFalse(array.isEmpty());
        array.clear();
        assertTrue(array.isEmpty());
    }

    /**
     * Test size
     */
    @Test
    public void testSize() {
        assertEquals(1, array.size());
        array.enqueue("1");
        assertEquals(2, array.size());    
    }

    /**
     * Test toArray
     */
    @Test
    public void testToArray() {
        array.enqueue("1");
        array.enqueue("2");
        Object[] newArray = array.toArray();
        assertEquals("[0, 1, 2]", Arrays.toString(newArray));
    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        assertEquals("[0]", array.toString());

        array.dequeue();
        assertEquals("[]", array.toString());

        array.enqueue("0");
        array.enqueue("1");
        array.enqueue("2");
        assertEquals("[0, 1, 2]", array.toString());
    }

    /**
     * Test equals
     */
    @Test
    public void testEquals() {
        assertTrue(array.equals(array));

        ArrayQueue<String> arrayNull = null;
        assertFalse(array.equals(arrayNull));

        String arrayString = "";
        assertFalse(array.equals(arrayString));

        ArrayQueue<String> array2 = new ArrayQueue<String>();
        array2.enqueue("1");
        array2.enqueue("2");
        assertFalse(array.equals(array2));

        ArrayQueue<String> array3 = new ArrayQueue<String>();
        array3.enqueue("1");
        assertFalse(array.equals(array3));

        ArrayQueue<String> array4 = new ArrayQueue<String>();
        array4.enqueue("1");
        array4.enqueue("0");
        array.enqueue("1");
        assertFalse(array.equals(array4));

        ArrayQueue<String> array5 = new ArrayQueue<String>();
        array5.enqueue("0");
        assertFalse(array.equals(array5));

        array.clear();

        array.enqueue("0");
        array.enqueue("1");
        ArrayQueue<String> array6 = new ArrayQueue<String>();
        array6.enqueue("0");
        array6.enqueue("1");
        assertTrue(array.equals(array6));
    }

}
