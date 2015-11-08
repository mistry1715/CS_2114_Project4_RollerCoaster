package rollercoaster;

import java.util.Comparator;

/**
 * This Comparator class can be used to order persons 
 * with height as the primary key and name as the secondary key. 
 * Persons will be ordered by height, if any two are the same 
 * height then those two are ordered by name.
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.24
 */
public class ComparePersonHeight implements Comparator<Object> {

    /**
     * Default constructor
     */
    public ComparePersonHeight() {
    }

    /**
     * Compare two persons
     * 
     * @param o1 First Person
     * @param o2 Second Person
     * @return return the difference
     */
    @Override
    public int compare(Object o1, Object o2) {
        int diff = ((Person)o1).getHeight() - ((Person)o2).getHeight();
        if (diff == 0) {
            diff = ((Person)o1).getName().compareTo(((Person)o2).getName());
        }
        return diff;
    }

}
