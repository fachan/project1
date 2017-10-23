package uber;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Helper class, containing static functions, to aid in the general tasks
 * that Uber must perform, such as parsing.
 * @author FaithChan
 *
 */
public class UberHelper {
   private static final String ENTRY_DELIMITER = ", ";
   private static final String CATEGORY_DELIMITER = ": ";
   private static final int CATEGORY_LOC = 0;
   private static final int VALUE_LOC = 1;
   
   /**
    * Writes the String str to PrintStreams ps and ps2.
    * @param str The String to be written.
    */
   public static void write(String str) {
      UberSimulator.ps.println(str);
      UberSimulator.ps2.println(str);
   }
   
   /**
    * Parses the User properties from a String from the input file and places 
    * them into a new HashMap.
    * @param properties The String from which to parse the properties.
    * @return The HashMap, initialized with the keys and values found when 
    * parsing the input line.
    */
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
   
   /**
    * Obtains the account type from the entry in the HashMap map.
    * @param map The HashMap in which to search for the AccountType.
    * @return The value of the AccountType enum found in the HashMap.
    */
   public static AccountType getAccountType(HashMap map) {
      String accountType = (String)map.get(UserProperty.ACCOUNT);
      return AccountType.valueOf(accountType.toUpperCase());
   }
   
   /**
    * Goes through the possible values in the UserProperty enum and attempts
    * to find one that matches the input string.
    * @param input The input string being searched for in the enum values.
    * @return The UserProperty value found in the search.
    */
   public static UserProperty getCategory(String input) {
      for (UserProperty p : UserProperty.values()) {
         if (p.name().equals(input)) {
            return p;
         }
      }
      
      return null;
   }
}
