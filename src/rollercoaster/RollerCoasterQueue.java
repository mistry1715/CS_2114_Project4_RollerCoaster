package rollercoaster;

import java.util.Arrays;
import java.util.Iterator;

import list.AList;

/**
 * This data structure represents the line of WaitingParties 
 * for the CoasterTrains. RollerCoasterQueue encapsulates an 
 * ArrayQueue. RollerCoasterQueue offers unique queue behavior 
 * because depending on how many seats are available on the coaster, 
 * it may ask a WaitingParty to split. It also offers roller 
 * coaster-specific enqueue behavior by checking for the height 
 * of people before allowing them in the roller coaster line.
 * 
 * @author JunjieCheng
 * @version 2015.10.29
 */
public class RollerCoasterQueue {

    /**
     * Minimum person height
     */
    public static final int MIN_PERSON_HEIGHT = 96;

    /**
     * Valid WaitingParty
     */
    private ArrayQueue<WaitingParty> queue;

    /**
     * Invalid WaitingParty
     */
    private AList<WaitingParty> shortiesParties;

    /**
     * Constructor
     */
    public RollerCoasterQueue() {
        queue = new ArrayQueue<WaitingParty>();
        shortiesParties = new AList<WaitingParty>();
    }

    /**
     * Push a WaitingParty to the queue. If the WaitingParty
     * is invalid, put the invalid person to the partyOfShorties
     * 
     * @param party The WaitingParty to be pushed
     */
    public void enqueueParty(WaitingParty party) {
        if (!validParty(party)) {
            WaitingParty partyOfShorties = createShortiesParty(party);
            shortiesParties.add(partyOfShorties);                        
            Iterator<Person> partyOfShortiesIterator = partyOfShorties.clone().iterator();

            while (partyOfShortiesIterator.hasNext()) {
                party.removePerson(partyOfShortiesIterator.next());
            }
        }
        queue.enqueue(party);
    }

    /**
     * Create and return the shortiesParty
     * 
     * @param party The WaitingParty to be checked
     * @return shortiesParty
     */
    private WaitingParty createShortiesParty(WaitingParty party) {
        WaitingParty partyOfShorties = new WaitingParty(true);
        Iterator<Person> iterator = party.iterator();

        while (iterator.hasNext()) {
            Person person = iterator.next();

            if (person.getHeight() < MIN_PERSON_HEIGHT) {
                if (party.willSplit()) {
                    partyOfShorties.add(person);
                }
                else {
                    return party;
                }
            }
        }

        return partyOfShorties;
    }

    /**
     * Check if the WaitingParty is valid
     * 
     * @param party The WaitingParty to be checked
     * @return Return true if it is valid, else return false
     */
    private boolean validParty(WaitingParty party) {
        Iterator<Person> iterator = party.iterator();

        while (iterator.hasNext()) {
            Person person = iterator.next();

            if (person.getHeight() < MIN_PERSON_HEIGHT) {
                return false;
            }
        }

        return true;
    }

    /**
     * Get rejected parties
     * 
     * @return shortiesParties
     */
    public AList<WaitingParty> rejectedParties() {
        return shortiesParties;
    }

    /**
     * Get the front WaitingParty
     * 
     * @return The front party in the queue
     */
    public WaitingParty getFront() {
        return queue.getFront();
    }

    /**
     * Pop a WaitingParty
     * 
     * @param seatsAvailable The maximum size of the party
     * @return A valid WaitingParty
     */
    public WaitingParty dequeueParty(int seatsAvailable) {
        if (queue == null) {
            return null;
        }
        else {
            WaitingParty party = getFront();

            if (party.getLength() > seatsAvailable) {
                return party;
            }
            else {
                if (party.willSplit()) {
                    return party.splitParty(seatsAvailable);
                }
                else {
                    return null;
                }
            }
        }
    }

    /**
     * Get the minimum height
     * 
     * @return MIN_PERSON_HEIGHT
     */
    public int getMinimumHeight() {
        return MIN_PERSON_HEIGHT;
    }

    /**
     * Transfer to String
     * 
     * @return String
     */
    public String toString() {
        return "Line with minimum height " + MIN_PERSON_HEIGHT + " cm.\n"
                + super.toString();
    }

    /**
     * Check if the queue is empty
     * 
     * @return Return true if it is empty, else return false
     */
    public boolean isEmpty() {
        return queue.size() == 0;
    }

    /**
     * Transfer the queue to Array
     * 
     * @return Array
     */
    public Object[] toArray() {
        return queue.toArray();
    }

    /**
     * Check if this and other are equal
     * 
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

            if (items.length != otherItems.length) {
                return false;
            }
            else {
                for (int i = 0; i < items.length; i++) {
                    if (!Arrays.equals(items, otherItems)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

}
