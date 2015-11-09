package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test RollerCoasterQueue class
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.31
 */
public class RollerCoasterQueueTest {

    /**
     * RolerCoasterQueue and WaitingParty for the test
     */
    private RollerCoasterQueue queue;
    private RollerCoasterQueue queue2;
    private RollerCoasterQueue queueNull;
    private WaitingParty party;
    private WaitingParty party2;
    private WaitingParty party3;
    private WaitingParty party4;
    private WaitingParty party5;

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        queue = new RollerCoasterQueue();
        queue2 = new RollerCoasterQueue();
        party = new WaitingParty(true);
        party2 = new WaitingParty(true);
        party3 = new WaitingParty(false);
        party4 = new WaitingParty(true);
        party5 = new WaitingParty(false);

        party.add(new Person("1", 100));

        party2.add(new Person("1", 100));
        party2.add(new Person("2", 50));

        party3.add(new Person("1", 100));
        party3.add(new Person("2", 50));

        party4.add(new Person("1", 100));
        party4.add(new Person("2", 100));

        party5.add(new Person("1", 100));
        party5.add(new Person("2", 100));
    }

    /**
     * Test enqueueParty when the parameter is valid
     */
    @Test
    public void testEnqueuePartyValid() {
        queue.enqueueParty(party);
        assertEquals(queue.getFront().toString(), party.toString());
    }

    /**
     * Test enqueueParty when the parameter is invalid
     */
    @Test
    public void testEnqueuePartyInvalid() {
        queue.enqueueParty(party2);
        assertEquals(queue.getFront().toString(), party.toString());
    }

    /**
     * Test enqueueParty when the parameter is invalid and will not split
     */
    @Test
    public void testEnqueuePartyWillNotSplit() {
        queue.enqueueParty(party3);
        assertEquals(queue.getFront().toString(), 
                "Party of size 0 will not split.\n[]\n");
    }

    /**
     * Test rejectedParties
     */
    @Test
    public void testRejectedParties() {
        queue.enqueueParty(party2);
        assertEquals(queue.rejectedParties().toString(), 
                "[Party of size 1 will split.\n[2 50cm]\n]");
    }

    /**
     * Test getFront
     */
    @Test
    public void testGetFront() {
        queue.enqueueParty(party);
        assertEquals(queue.getFront(), party);
    }

    /**
     * Test dequeue
     */
    @Test
    public void testDequeue() {
        assertNull(queue.dequeueParty(1));

        queue.enqueueParty(party4);
        assertEquals(queue.dequeueParty(2), party4);

        queue.enqueueParty(party4);
        assertEquals(queue.dequeueParty(1), party);
        queue.dequeueParty(1);

        queue.enqueueParty(party5);
        assertNull(queue.dequeueParty(1));
    }

    /**
     * Test getMinimumHeight
     */
    @Test
    public void testGetMinimumHeight() {
        assertEquals(queue.getMinimumHeight(), 96);
    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        queue.enqueueParty(party);
        String str = "Line with minimum height 96 cm.\n"
                + "[Party of size 1 will split.\n[1 100cm]\n]";
        assertEquals(queue.toString(), str);
    }

    /**
     * Test isEmpty
     */
    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueueParty(party);
        assertFalse(queue.isEmpty());
    }

    /**
     * Test toArray
     */
    @Test
    public void testToArray() {
        queue.enqueueParty(party);
        WaitingParty array = (WaitingParty) queue.toArray()[0];
        assertTrue(array.equals(party));
    }

    /**
     * Test equals
     */
    @Test
    public void testEquals() {
        assertFalse(queue.equals(queueNull));
        assertTrue(queue.equals(queue));
        assertFalse(queue.equals(party));

        queue.enqueueParty(party);
        queue2.enqueueParty(party);
        assertTrue(queue.equals(queue2));

        queue2.enqueueParty(party);
        assertFalse(queue.equals(queue2));
    }

    /**
     * Test equals when two queue are different but have same length
     */
    @Test
    public void testEqualsDifferent() {
        queue.enqueueParty(party);
        queue.enqueueParty(party);
        queue2.enqueueParty(party4);
        queue.enqueueParty(party4);

        assertFalse(queue.equals(queue2));
    }


}
