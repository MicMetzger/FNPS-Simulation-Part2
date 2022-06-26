package main.java.com.Logging;

import java.util.*;
import main.java.com.individuals.*;
import main.java.com.item.*;
import main.java.com.item.supplies.*;
import main.java.com.utilities.*;



public class EventLog {
  // public static final int NUM_STORES = 3;
  public final  EventType             EVENTTYPE;
  public final  Employee              EMPLOYEEONE;
  public final  Employee              EMPLOYEETWO;
  public final  Pet                   PET;
  public final  List<Pet>             PETS;
  public final  Supplies              SUPPLY;
  public final  List<Supplies>        SUPPLIES;
  private final Customer              CUSTOMER;
  private final List<Customer>        CUSTOMERS;
  public final  Pair<int[], double[]> EARNING;
  public final  int[]                 DATA;
  public final  Integer               DAY;
  public final  Pair<Double, Double>  CASH;

  private EventLog(EventType eventType, Employee employeeOne, Employee employeeTwo, Pet pet, List<Pet> pets, Supplies supply, List<Supplies> supplies,
      Customer customer, List<Customer> customers, Pair<int[], double[]> earning, Integer day, int[] data, double withdrawl, double cash) {
    this.EVENTTYPE   = eventType;
    this.EMPLOYEEONE = employeeOne;
    this.EMPLOYEETWO = employeeTwo;
    this.PET         = pet;
    this.PETS        = pets;
    this.SUPPLY      = supply;
    this.SUPPLIES    = supplies;
    this.CUSTOMER    = customer;
    this.CUSTOMERS   = customers;
    this.EARNING     = earning;
    this.DAY         = day;
    this.DATA        = data;
    this.CASH        = new Pair<Double, Double>(withdrawl, cash);
  }


  public static EventLog newDayEvent(int day) {
    return new EventLog(EventType.EVENT_NEWDAY, null, null, null, null, null, null, null, null, null, day, null, 0.0, 0.0);
  }


  public static EventLog startDayEvent(List<Employee> employees, int employeeCount, List<Pet> pets, int petCount, List<Supplies> supplies,
      int supplyCount) {
    int[] info = new int[1];
    info[0] = employeeCount;
    return new EventLog(EventType.EVENT_ARRIVE, employees.get(0), employees.get(1), null, pets, null, supplies,
        null, null, null, null, null, 0.0, 0.0);
  }

  public static EventLog bankingEvent(Employee employee, double withdrawl, double cash) {
    return new EventLog(EventType.EVENT_BANKING, employee, null, null, null, null, null,
        null, null, null, null, null, withdrawl, cash);
  }


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
    EVENT_FEEDING("FeedTheAnimals: ");

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
