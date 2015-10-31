package rollercoaster;

/**
 * A CoasterTrain holds an array of Persons. It seats people into 
 * the array and keeps track of available seats.
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.31
 */
public class CoasterTrain {

    /**
     * Array of Person
     */
    private Person[] seat;

    /**
     * Fist available position
     */
    private int firstAvail;

    /**
     * Maximum number of seats
     */
    public static final int SEATS = 20;

    /**
     * Constructor
     */
    public CoasterTrain() {
        seat = new Person[SEATS];
        firstAvail = 0;
    }

    /**
     * Get number of empty seat
     * 
     * @return number of empty seat
     */
    public int getOpenSeats() {
        return SEATS - firstAvail;
    }

    /**
     * Clear
     */
    public void clear() {
        seat = new Person[SEATS];
        firstAvail = 0;
    }

    /**
     * Check if seat is empty
     * 
     * @return Return true if seat is empty, else return false
     */
    public boolean isEmpty() {
        return firstAvail == 0;
    }

    /**
     * Transfer seat to String
     * 
     * @return String
     */
    public String toString() {
        String str = "";

        for (int i = 0; i < getOpenSeats(); i++) {
            str += seat[i] + "";

            if (getOpenSeats() - i > 1) {
                str += " ";
            }
        }

        return str;
    }

    /**
     * Set a WaitingParty to seat
     * 
     * @param party WaitingParty
     * @throws IllegalStateException
     */
    public void seatParty(WaitingParty party) throws IllegalStateException {
        if (party.getLength() > getOpenSeats()) {
            throw new IllegalStateException();
        }
        else {
            int j = 0;

            for (int i = firstAvail; i < party.getLength(); i++) {
                seat[i] = party.getEntry(j++);
            }
        }
    }

    /**
     * Check if this and other are equal
     * 
     * @param other The Object to be checked
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
            for (int i = 0; i < SEATS; i++) {
                if (seat[i].equals(((CoasterTrain)other).toArray()[i])) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * Get array
     * 
     * @return seat
     */
    public Person[] toArray() {
        return seat;
    }
}
