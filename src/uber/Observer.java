package uber;

import java.util.LinkedList;

public interface Observer {
   double update(Driver driver, Customer customer, LinkedList<Location> route);
}
