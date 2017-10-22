package uber;

public class Car {
   private String type;
   private int capacity;
   
   public Car(String type, int capacity) {
      this.type = type;
      this.capacity = capacity;
   }
   
   public String getType() {
      return this.type;
   }
   
   public int getCapacity() {
      return this.capacity;
   }
}
