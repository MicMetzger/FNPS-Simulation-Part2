package main.java.com;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;



public record Builders() {

  // Data helpers
  public static final ArrayList<String> COLORS              =
      new ArrayList<String>(Arrays.asList("Black", "Brown", "White", "Gray", "Red"));
  
  public static final boolean[]         randomSelectionbool = {true, false};
  
  public static final DecimalFormat     sizeFormat          = new DecimalFormat("#####.00");

  public static ArrayList<String> NAME_TEMPLATE = new ArrayList<String>(Arrays.asList("Chris", "Paul", "Jack", "Alex", "John"));

}
