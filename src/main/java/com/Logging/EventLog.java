package main.java.com.Logging;

import java.util.*;
import main.java.com.individuals.*;
import main.java.com.item.*;
import main.java.com.item.supplies.*;
import utilities.*;



public class EventLog {
  // public static final int NUM_STORES = 3;
  public final  EventType                   EVENTTYPE;
  public final  Employee                    EMPLOYEEONE;
  public final  Employee                    EMPLOYEETWO;
  public final  List<Employee>              EMPLOYEES;
  public final  Pet                         PET;
  public final  List<Pet>                   PETS;
  public final  Supplies                    SUPPLY;
  public final  List<Supplies>              SUPPLIES;
  private final Customer                    CUSTOMER;
  private final List<Customer>              CUSTOMERS;
  public final  List<Pair<Integer, Double>> EARNINGS;
  public final  int[]                       DATA;
  public final  Integer                     DAY;
  public final  Pair<Double, Double>        CASH;

  private EventLog(EventType eventType, Employee employeeOne, Employee employeeTwo, List<Employee> employees, Pet pet, List<Pet> pets,
      Supplies supply, List<Supplies> supplies, Customer customer, List<Customer> customers,
      List<Pair<Integer, Double>> earnings, Integer day, int[] data, double withdrawl, double cash) {
    this.EVENTTYPE   = eventType;
    this.EMPLOYEEONE = employeeOne;
    this.EMPLOYEETWO = employeeTwo;
    this.EMPLOYEES   = employees;
    this.PET         = pet;
    this.PETS        = pets;
    this.SUPPLY      = supply;
    this.SUPPLIES    = supplies;
    this.CUSTOMER    = customer;
    this.CUSTOMERS   = customers;
    this.EARNINGS    = earnings;
    this.DAY         = day;
    this.DATA        = data;
    this.CASH        = new Pair<Double, Double>(cash, withdrawl);
    // this.CASH.add(earning);

  }


  public static EventLog newDayEvent(int day) {
    return new EventLog(EventType.EVENT_NEWDAY, null, null, null, null, null, null, null, null, null, null, day, null, 0.0, 0.0);
  }


  public static EventLog startDayEvent(List<Employee> employees, int employeeCount, List<Pet> pets, int petCount, List<Supplies> supplies,
      int supplyCount) {
    int[] info = new int[1];
    info[0] = employeeCount;
    return new EventLog(EventType.EVENT_ARRIVE, employees.get(0), employees.get(1), employees, null, pets, null, supplies,
        null, null, null, null, null, 0.0, 0.0);
  }

  public static EventLog bankingEvent(Employee employee, double withdrawl, double cash) {
    return new EventLog(EventType.EVENT_BANKING, employee, null, null, null, null, null, null,
        null, null, null, null, null, withdrawl, cash);
  }

  public static EventLog petEvent(Employee employee, Pet pet) {
    return new EventLog(EventType.EVENT_FEEDING, employee, null, null, pet, null, null, null,
        null, null, null, null, null, 0.0, 0.0);
  }

  public static EventLog suppliesEvent(Employee employee, Supplies supply) {
    return new EventLog(EventType.EVENT_PROCESSING, employee, null, null, null, null, supply, null,
        null, null, null, null, null, 0.0, 0.0);
  }

  public static EventLog customerEvent(Employee employee, Customer customer) {
    return new EventLog(EventType.EVENT_SELLING, employee, null, null, null, null, null, null,
        null, null, null, null, null, 0.0, 0.0);
  }

  public static EventLog tracking(List<Employee> employees, List<Pair<Integer, Double>> earning, int day) {
    return new EventLog(EventType.EVENT_TRACKING, null, null, employees, null, null, null, null,
        null, null, earning, day, null, 0.0, 0.0);
  }
  
  /*
  public static EventLog employeeEvent(Employee employee, Employee employeeTwo) {
    return new EventLog(EventType.EVENT_WORKING, employee, employeeTwo, null, null, null, null, null,
        null, null, null, null, null, 0.0, 0.0);
  }
  */


  @Override
  public String toString() {
    switch (EVENTTYPE) {
      case EVENT_NEWDAY -> {
        return "\n" +
               EVENTTYPE +
               "\n Day: " + DAY +
               "\n";
      }
      case EVENT_ARRIVE -> {
        return "EventLog {\n" +
               "\t" + EVENTTYPE +
               "\n\t Employee #1: " + EMPLOYEEONE.getName() +
               "\n\t Employee #2: " + EMPLOYEETWO.getName() +
/*                "\n Employee Count: " + DATA[0] +
               ",\n Pet Count: " + DATA[1] +
               ",\n Supply Count: " + DATA[2] + */
               "\n}";
      }
      case EVENT_BANKING -> {
        return "EventLog {\n" +
               "\t" + EVENTTYPE +
               "\n\t Employee: " + EMPLOYEEONE.getName() +
               "\n\t Withdrawl: " + CASH.first +
               ",\n\t Cash: " + CASH.second +
               "\n}";
      }
      case EVENT_TRACKING -> {
        StringBuilder sb = new StringBuilder();
        sb.append("EventLog {\n");
        sb.append("\t").append(EVENTTYPE).append("Day #").append(DAY).append("\n");
        sb.append(String.format("\t%-20s \t%-20s \t%-20s\n", "Employee", "Sold", "Earnings Total"));
        for (int i = 0; i < EMPLOYEES.size(); i++) {
          sb.append(String.format("\t%-20s", EMPLOYEES.get(i).getNameSimple()));
          sb.append(String.format("\t%-20s", EARNINGS.get(i).first.toString()));
          sb.append(String.format("\t%-20s\n", EARNINGS.get(i).second.toString()));
        }
        return sb.toString();
      }

      default -> {return "\n";}
    }
  }


  public enum EventType {
    EVENT_SELLING("PlaceAnOrder: "),
    EVENT_NEWDAY("NewDay: "),
    EVENT_ARRIVE("ArriveAtStore: "),
    EVENT_OPEN("StoreOpen: "),
    EVENT_CLOSING("LeaveTheStore: "),
    EVENT_PROCESSING("ProcessDeliveries: "),
    EVENT_INVENTORY("DoInventory:"),
    EVENT_TRAINING("DoTraining: "),
    EVENT_CLEANING("CleanTheStore: "),
    EVENT_BANKING("GoToBank: "),
    EVENT_FEEDING("FeedTheAnimals: "),
    EVENT_TRACKING("Tracker: ");

    private final String eventType;

    EventType(String s) {
      eventType = s;
    }

    @Override
    public String toString() {
      return eventType;
    }


  }


}
