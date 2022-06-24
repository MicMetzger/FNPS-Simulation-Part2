package main.java.com;

import java.io.*;
import java.util.*;
import main.java.com.individuals.*;



public class TrackerRepository {
  private static final Object mutex       = new Object();
  private static final File   trackerFile = new File("tracker.txt");
  private static final String DELIMITER   = ",";
  private static final String NEW_LINE    = "\n";
  private static final String HEADER      = "Name,Items Sold,Purchase Price Total";

  // private Employee[] employees;   // array of employees from this simulation
  // private double     priceTotal;  // Pet history     
  // private int        itemSold;    // Item history
  Map<Employee, Pair<Integer, Double>> employeeHistory;

  private TrackerRepository() {
    employeeHistory = new HashMap<Employee, Pair<Integer, Double>>();
  }

  private static final class InstanceHolder {

    private static final TrackerRepository instance = new TrackerRepository();
  }

  public static TrackerRepository getInstance() {
    return InstanceHolder.instance;
  }

  public void LOG(Employee employee, double price) { 
    if (employeeHistory.containsKey(employee)) {
      Pair<Integer, Double> history = employeeHistory.get(employee);
      history.first++;
      history.second += price;

    } else {
      Pair<Integer, Double> history = new Pair<Integer, Double>(1, price);
      employeeHistory.put(employee, history);
    }
  }


  /**
   * The type Pair.
   * A simple pair class to store two values.
   * @param <K> the type parameter representing the first value
   * @param <V> the type parameter representing the second value
   */
  class Pair<K, V> {
    public K first;
    public V second;

    public Pair(K key, V value) {
      this.first  = key;
      this.second = value;
    }

    public <K, V> Pair<K, V> of(K a, V b) {
      return new Pair<>(a, b);
    }

    // Pair<?, ?> pair = (Pair<?, ?>) o;

  }

}
