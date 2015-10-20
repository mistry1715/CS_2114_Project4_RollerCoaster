package rollercoaster;

import java.util.Comparator;

public class ComparePersonHeight implements Comparator<Object> {
    
    public ComparePersonHeight() {
        
    }
    
    @Override
    public int compare(Object o1, Object o2) {
       int diff = ((Person)o1).getHeight() - ((Person)o2).getHeight();
       if (diff == 0) {
               diff = ((Person)o1).getName().compareTo(((Person)o2).getName());
       }
       return diff;
   }
    
}
