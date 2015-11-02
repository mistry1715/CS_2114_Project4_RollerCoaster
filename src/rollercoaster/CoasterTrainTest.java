package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test CoasterTrain class
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.11.01
 */
public class CoasterTrainTest {

    /**
     * CoasterTrain and WaitinghParty for the test
     */
    private CoasterTrain train;
    private CoasterTrain train2;
    private CoasterTrain train3;
    private CoasterTrain trainNull;
    private WaitingParty party;
    private WaitingParty party2;

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        train = new CoasterTrain();
        train2 = new CoasterTrain();
        train3 = new CoasterTrain();
        new CoasterTrain();
        party = new WaitingParty(true);
        party2 = new WaitingParty(true);
        new WaitingParty(true);

        party.add(new Person("1", 100));
        party.add(new Person("2", 100));

        for (int i = 1; i < 30; i++) {
            party2.add(new Person(i + "", 100));
        }
    }

    /**
     * Test getOpenSeats
     */
    @Test
    public void testGetOpenSeats() {
        assertEquals(train.getOpenSeats(), 20);
    }

    /**
     * Test clear
     */
    @Test
    public void testClear() {
        train.clear();
        assertTrue(train.isEmpty());
    }

    /**
     * Test isEmpty
     */
    @Test
    public void testIsEmpty() {
        assertTrue(train.isEmpty());
    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        train.seatParty(party);
        assertEquals(train.toString(), "1 100cm 2 100cm");
    }

    /**
     * Test seatParty
     */
    @Test
    public void testSeatParty() {
        Exception ex = null;

        try {
            train.seatParty(party2);
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);

        train.seatParty(party);
        assertEquals(train.toString(), "1 100cm 2 100cm");
    }

    /**
     * Test equals
     */
    @Test
    public void testEquals() {
        train.seatParty(party);
        train2.seatParty(party);

        assertFalse(train.equals(trainNull));
        assertTrue(train.equals(train));
        assertFalse(train.equals(party));
        assertTrue(train.equals(train2));
        assertFalse(train.equals(train3));
    }

    /**
     * Test toArray
     */
    @Test
    public void testToArray() {
        train.seatParty(party);

        for (int i = 0; i < 2; i++) {
            assertTrue(train.toArray()[i].equals(party.toArray()[i]));
        }
    }

}
