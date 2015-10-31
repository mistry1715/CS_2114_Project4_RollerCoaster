package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RollerCoasterQueueTest {

    private RollerCoasterQueue queue;
    private WaitingParty party;
    private WaitingParty party2;
    private WaitingParty party3;
    private WaitingParty party4;
    private WaitingParty party5;

    @Before
    public void setUp() throws Exception {
        queue = new RollerCoasterQueue();
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

    @Test
    public void testEnqueuePartyValid() {
        queue.enqueueParty(party);
        assertEquals(queue.getFront().toString(), party.toString());
    }

    @Test
    public void testEnqueuePartyInvalid() {
        queue.enqueueParty(party2);
        assertEquals(queue.getFront().toString(), party.toString());
    }

    @Test
    public void testEnqueuePartyWillNotSplit() {
        queue.enqueueParty(party3);
        assertEquals(queue.getFront().toString(), 
                "Party of size 0 will not split.\n[]\n");
    }

    @Test
    public void testRejectedParties() {
        queue.enqueueParty(party2);
        assertEquals(queue.rejectedParties().toString(), "[Party of size 1 will split.\n[2 50cm]\n]");
    }

    @Test
    public void testGetFront() {
        queue.enqueueParty(party);
        assertEquals(queue.getFront(), party);
    }

    @Test
    public void testDequeue() {
        assertNull(queue.dequeueParty(1));

        queue.enqueueParty(party4);
        assertEquals(queue.dequeueParty(2), party4);

        queue.enqueueParty(party4);
        assertEquals(queue.dequeueParty(1), party);

        queue.enqueueParty(party5);
        assertNull(queue.dequeueParty(1));
    }

    @Test
    public void testGetMinimumHeight() {
        assertEquals(queue.getMinimumHeight(), 96);
    }

    @Test
    public void testToString() {
        queue.enqueueParty(party);
        String str = "Line with minimum height 96 cm.\n"
                + "[Party of size 1 will split.\n[1 100cm]\n]";
        assertEquals(queue.toString(), str);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueueParty(party);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testToArray() {
        queue.enqueueParty(party);
        WaitingParty[] array = (WaitingParty[]) queue.toArray();
        assertTrue(array[0].equals(party));
    }

}
