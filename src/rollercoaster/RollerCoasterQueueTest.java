package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RollerCoasterQueueTest {
    
    private RollerCoasterQueue queue;
    private WaitingParty party;
    private WaitingParty party2;
    private WaitingParty party3;

    @Before
    public void setUp() throws Exception {
        queue = new RollerCoasterQueue();
        party = new WaitingParty(true);
        party2 = new WaitingParty(true);
        party3 = new WaitingParty(false);
        
        party.add(new Person("1", 100));
        
        party2.add(new Person("1", 100));
        party2.add(new Person("2", 50));
        
        party3.add(new Person("1", 100));
        party3.add(new Person("2", 50));
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

}
