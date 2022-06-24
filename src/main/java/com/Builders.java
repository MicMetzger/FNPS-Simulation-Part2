package main.java.com;

import java.text.*;
import java.util.*;



public record Builders() {

  // Data helpers
  public static final ArrayList<String> COLORS              =
      new ArrayList<String>(Arrays.asList("Black", "Brown", "White", "Gray", "Red"));
  
  public static final boolean[]         randomSelectionbool = {true, false};
  
  public static final DecimalFormat     sizeFormat          = new DecimalFormat("#####.00");

  public static ArrayList<String> NAME_TEMPLATE = new ArrayList<String>(Arrays.asList("Kevin", "Andrew", "Michelle", "Chris", "Paul", "Jack", "Alex", "John", "David", "Sarah"));

}
