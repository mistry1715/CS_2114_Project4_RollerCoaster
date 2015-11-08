package rollercoaster;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import list.AList;

/**
 * WaitingParty extends AList. 
 * WaitingParties hold the groups of Persons who want to ride together. 
 * A WaitingParty can be split if the people in the party are willing to 
 * be separated. Data from the input files will be parsed into 
 * WaitingParty objects.
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.24
 */
public class WaitingParty extends AList<Person> implements Iterable<Person> {

    /**
     * Set to true if the WaitingParty can be split
     */
    final boolean willSplit;

    /**
     * Constructor
     * 
     * @param willSplit The WaitingParty can or cannot be split
     */
    public WaitingParty(boolean willSplit) {
        super();
        this.willSplit = willSplit;
    }

    /**
     * Check the size of the WaitingParty and return a valid size of 
     * WaitingParty
     * 
     * @param maxSize Maximum size of the WaitingParty
     * @return Valid size of WaitingParty
     */
    public WaitingParty splitParty(int maxSize) {
        if (maxSize >= super.getLength()) {
            return this;
        }
        else if (!willSplit) {
            return null;
        }
        else {
            WaitingParty splitParty = new WaitingParty(true);

            for (int i = 0; i < maxSize; i++) {
                splitParty.add(super.remove(0));
            }

            return splitParty;
        }
    }

    /**
     * Return the willSplit
     * 
     * @return willSplit
     */
    public boolean willSplit() {
        return willSplit;
    }

    /**
     * Remove the given Person from the waitingParty,
     * return false if the Person is not in the WaitingParty
     * 
     * @param person The Person to remove
     * @return Return true if removed, else return false
     */
    public boolean removePerson(Person person) {
        if (super.isEmpty()) {
            return false;
        }
        else {
            for (int i = 0; i < super.getLength(); i++) {
                if (super.getEntry(i).getName() == person.getName()
                        && super.getEntry(i).getHeight() == 
                        person.getHeight()) {
                    super.remove(i);
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Transfer the WaitingParty to String
     * 
     * @return String
     */
    public String toString() {
        if (willSplit) {
            return "Party of size " + super.getLength() 
                + " will split.\n" + super.toString() + "\n";
        }
        else {
            return "Party of size " + super.getLength() 
                + " will not split.\n" + super.toString() + "\n";
        }
    }

    /**
     * Check if other and this is equal
     * 
     * @param other The Object to be compared
     * @return Return true if they are equal, else return false
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        else if (this == other) {
            return true;
        }
        else if (this.getClass() != other.getClass()) {
            return false;
        }
        else {
            Object[] items = this.toArray(); 
            Object[] otherItems = ((WaitingParty)other).toArray();
            ComparePersonHeight comparer = new ComparePersonHeight();
            Arrays.sort(items, comparer);
            Arrays.sort(otherItems, comparer);

            return Arrays.equals(items, otherItems);
        }
    }

    /**
     * Return a iterator of the WaitingParty
     * 
     * @return Iterator
     */
    @Override
    public Iterator<Person> iterator() {
        return new WaitingPartyIterator();
    }

    /**
     * Get a clone of the WaitingParty
     * 
     * @return clone
     */
    @Override
    public WaitingParty clone() {
        WaitingParty clone = new WaitingParty(this.willSplit());
        Iterator<Person> iterator = this.iterator();

        while (iterator.hasNext()) {
            clone.add(iterator.next());
        }

        return clone;
    }

    /**
     * WaitingParty Iterator
     * 
     * @author Junjie Cheng (cjunjie)
     * @version 2015.10.25
     */
    private class WaitingPartyIterator implements Iterator<Person> {

        /**
         * Index of the Iterator
         */
        private int index;

        /**
         * Constructor
         */
        private WaitingPartyIterator() {
            index = 0;
        }

        /**
         * Check if has next entry
         * 
         * @return Return true if has next entry, else
         *          return false
         */
        @Override
        public boolean hasNext() {
            return index < getLength();
        }

        /**
         * Return the next entry
         * Throw Exception if has no next
         * 
         * @return Next entry
         */
        @Override
        public Person next() {
            if (hasNext()) {
                return getEntry(index++);
            }
            else {
                throw new NoSuchElementException();
            }
        }

    }

}
