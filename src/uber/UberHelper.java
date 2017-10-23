package uber;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class UberHelper {
   private static final String ENTRY_DELIMITER = ", ";
   private static final String CATEGORY_DELIMITER = ": ";
   private static final int CATEGORY_LOC = 0;
   private static final int VALUE_LOC = 1;
   
   public static void write(String str) {
      UberSimulator.ps.println(str);
      UberSimulator.ps2.println(str);
   }
   
   public static HashMap parseProperties(String properties) {
      String[] entry;
      UserProperty property;
      HashMap newMap = new HashMap();
      
      Scanner in = new Scanner(properties);
      in.useDelimiter(ENTRY_DELIMITER);
      
      while (in.hasNext()) {
         entry = in.next().split(CATEGORY_DELIMITER);
         property = getCategory(entry[CATEGORY_LOC]);
        
         if (property == null) {
            System.out.println("Entry " + entry[CATEGORY_LOC] + 
                  " not found. Skipping");
            continue;
         }
         
         newMap.put(property, entry[VALUE_LOC]);
      }
      
      in.close();
      return newMap;
   }
   
   public static AccountType getAccountType(HashMap map) {
      String accountType = (String)map.get(UserProperty.ACCOUNT);
      return AccountType.valueOf(accountType.toUpperCase());
   }
   
   public static UserProperty getCategory(String input) {
      for (UserProperty p : UserProperty.values()) {
         if (p.name().equals(input)) {
            return p;
         }
      }
      
      return null;
   }
}
