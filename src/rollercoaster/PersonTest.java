package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Person Class
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.24
 */
public class PersonTest {

    /**
     * Instances of Person for the test
     */
    private Person person;
    private Person personNull;
    private Person personDifferent;
    private Person personSame;

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        person = new Person("Junjie", 183);
        personNull = null;
        personDifferent = new Person("Yichen", 187);
        personSame = new Person("Cheng", 183);
    }

    /**
     * Test getName
     */
    @Test
    public void testGetName() {
        assertEquals(person.getName(), "Junjie");
    }

    /**
     * Test getHeight
     */
    @Test 
    public void testGetHeight() {
        assertEquals(person.getHeight(), 183);
    }

    /**
     * Test toString
     */
    @Test
    public void testToString() {
        assertEquals(person.toString(), "Junjie 183cm");
    }

    /**
     * Test Equals
     */
    @Test
    public void testEquals() {
        assertFalse(person.equals(personNull));
        assertTrue(person.equals(person));
        assertFalse(person.equals("Junjie"));
        assertFalse(person.equals(personDifferent));
        assertTrue(person.equals(personSame));
    }

}
