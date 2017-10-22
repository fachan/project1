package uber;

import java.util.LinkedList;

public interface Subject {
   public void registerObserver(Observer o);
   public void removeObserver(Observer o);
   public void notifyObservers(Driver driver, Customer customer, 
         LinkedList<Location> route);
}
