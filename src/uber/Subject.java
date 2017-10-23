package uber;

import java.util.LinkedList;

/**
 * Subject interface to serve as a counterpart to Observer. Contains the 
 * necessary methods to communicate with any Observers.
 * @author FaithChan
 *
 */
public interface Subject {
   public void registerObserver(Observer o);
   public void removeObserver(Observer o);
   public void notifyObservers(Driver driver, Customer customer, 
         LinkedList<Location> route);
}
