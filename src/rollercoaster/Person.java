package rollercoaster;

public class Person {
    
    String name;
    int height;
    
    public Person(String name, int height) {
        this.name = name;
        this.height = height;
    }
    
    public String getName() {
        return name;
    }
    
    public int getHeight() {
        return height;
    }
    
    public String toString() {
        return getName() + " " + getHeight() + "cm";
    }
    
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
