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

    /**
     * Set up before the test
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        person = new Person("Junjie", 183);
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
        assertTrue(person.equals(person));

        Person personNull = null;
        assertFalse(person.equals(personNull));

        String personString = "";
        assertFalse(person.equals(personString));

        Person personName = new Person("YiChen", 183);
        assertFalse(person.equals(personName));

        Person personHeight = new Person("Junjie", 180);
        assertFalse(person.equals(personHeight));

        Person personDifferent = new Person("YiChen", 189);
        assertFalse(person.equals(personDifferent));

        Person personSame = new Person("Junjie", 183);
        assertTrue(person.equals(personSame));
    }

}
