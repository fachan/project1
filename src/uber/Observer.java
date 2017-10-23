package uber;

import java.util.LinkedList;

/**
 * Observer interface; an Object that implements this is notified by an Object
 * that implements Subject.
 * @author FaithChan
 *
 */
public interface Observer {
   double update(Driver driver, Customer customer, LinkedList<Location> route);
}
