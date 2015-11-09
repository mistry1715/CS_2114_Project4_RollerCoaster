package rollercoaster;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

/**
 * Test WaitingParty
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.25
 */
public class WaitingPartyTest {

    /**
     * WaitingParty for the test
     */
    private WaitingParty list;
    private WaitingParty list2;
    private WaitingParty list3;
    private WaitingParty list4;
    private WaitingParty listEmpty;
    private WaitingParty listNull;

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        list = new WaitingParty(true);       
        list.add(new Person("Junjie", 183));
        list.add(new Person("Yichen", 187));
        list.add(new Person("Peng", 175));

        list2 = new WaitingParty(true); 
        list2.add(new Person("Junjie", 183));
        list2.add(new Person("Yichen", 187));

        list3 = new WaitingParty(false);
        list3.add(new Person("Junjie", 183));
        list3.add(new Person("Yichen", 187));

        list4 = new WaitingParty(true);
        list4.add(new Person("Junjie", 183));
        list4.add(new Person("Yichen", 187));
        
        listEmpty = new WaitingParty(true);
        listNull = null;
    }

    /**
     * Test splitParty
     */
    @Test
    public void testSplitParty() {
        assertEquals(list.splitParty(5), list);
        assertEquals(list.splitParty(2), list2);
        assertNull(list3.splitParty(1));
    }

    /**
     * Test willSplit
     */
    @Test
    public void testWillSplit() {
        assertTrue(list.willSplit());
        assertFalse(list3.willSplit());
    }

    /**
     * Test removePerson
     */
    @Test
    public void testRemovePerson() {
        assertFalse(listEmpty.removePerson(new Person("Junjie", 183)));
        assertTrue(list.removePerson(new Person("Junjie", 183)));
        assertFalse(list.removePerson(new Person("Xinchen", 170)));
        assertFalse(list.removePerson(new Person("Junjie", 170)));
    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        assertEquals(list2.toString(), 
                "Party of size 2 will split.\n[Junjie 183cm, Yichen 187cm]\n");
        assertEquals(list3.toString(), 
                "Party of size 2 will not split.\n" + ""
                        + "[Junjie 183cm, Yichen 187cm]\n");
    }

    /**
     * Test equals
     */
    @Test
    public void testEquals() {
        assertFalse(list.equals(listNull));
        assertTrue(list.equals(list));
        assertFalse(list.equals("Junjie"));
        assertFalse(list.equals(list2));
        assertTrue(list2.equals(list4));
    }

    /**
     * Test iterator
     */
    @Test
    public void testIterator() {
        Iterator<Person> iterator = list2.iterator();

        assertEquals(iterator.next().toString(), "Junjie 183cm");
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next().toString(), "Yichen 187cm");
        assertFalse(iterator.hasNext());

        Exception ex = null;

        try {
            iterator.next();
        }
        catch (Exception e) {
            ex = e;
        }

        assertNotNull(ex);
    }

    /**
     * Test clone
     */
    @Test
    public void testClone() {
        WaitingParty clone = list.clone();
        assertTrue(list.equals(clone));
    }

}
