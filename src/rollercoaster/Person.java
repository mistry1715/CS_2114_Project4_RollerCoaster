package rollercoaster;

/**
 * These objects contain a string, for a person��s name, 
 * and an int, for their height in centimeters.
 * 
 * @author Junjie Cheng (cjunjie)
 * @version 2015.10.24
 */
public class Person {

    /**
     * Name
     */
    String name;
    
    /**
     * Height
     */
    int height;

    /**
     * Constructor
     * 
     * @param name The name of the person
     * @param height The height of the person
     */
    public Person(String name, int height) {
        this.name = name;
        this.height = height;
    }

    /**
     * Get person's name
     *         
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get person's height
     * 
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Using a StringBuilder, concatenate the name, 
     * the height value, and the abbreviation for centimeters.
     * 
     * @return a String contains name and height
     */
    public String toString() {
        return getName() + " " + getHeight() + "cm";
    }

    /**
     * Check if this and the Object are same class and 
     * have same height
     * 
     * @param other The Object for compare
     * @return return true if this and other are equal,
     *          else return false
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        else if (other == this) {
            return true;
        }
        else if (other.getClass() != this.getClass()) {
            return false;
        }
        else {
            return ((Person)other).getHeight() == this.getHeight();
        }
    }
}
