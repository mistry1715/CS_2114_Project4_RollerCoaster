package rollercoaster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test ComparePersonHeight class
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.24
 */
public class ComparePersonHeightTest {

    /**
     * Variables for the test
     */
    private Person person1;
    private Person person2;
    private Person person3;
    private ComparePersonHeight comparator;

    /**
     * Set up before the test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        person1 = new Person("Junjie", 183);
        person2 = new Person("Yichen", 187);
        person3 = new Person("Peng", 183);
        comparator = new ComparePersonHeight();
    }

    /**
     * Test compare
     */
    @Test
    public void testCompare() {
        assertEquals(comparator.compare(person1, person2), -4);
        assertEquals(comparator.compare(person1, person3), -6);
    }

}
