package main.java.com.events.task;

import static main.java.com.events.EventStatus.COMPLETE;
import static main.java.com.events.EventStatus.INCOMPLETE;
import static main.java.com.events.EventStatus.IN_PROGRESS;

import java.io.*;
import main.java.com.Logging.*;
import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.item.*;
import main.java.com.store.*;



public class GoEndSimulation implements State {
  Store       state;
  EventStatus status;

  public GoEndSimulation(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = IN_PROGRESS;

    System.out.println("\n\n_______________ STATS _______________");
    System.out.println("Total Cash: $" + state.getCash());
    System.out.println("Total withdrawal: $" + state.bankWithdrawal);
    System.out.println("_______________________________________________");

    System.out.println("\n\n_______________ Items Sold _______________");
    for (Item item : state.getSoldItems()) {
      System.out.println(
          item.getName() + " $" + item.getSalePrice() + ", Sold on: DAY " + item.getDaySold());
    }
    System.out.println("_______________________________________________");

    System.out.println("\n\n_______________ Remaining Items _______________");
    for (Item item : state.getInventory()) {
      System.out.println(item.getName() + ", Value: $" + item.getListPrice());
    }
    System.out.println("_______________________________________________");

    System.out.println("\n\n____________ Remaining Sick Animals ___________");
    for (Pet item : state.getSick()) {
      System.out.println(item.getName() + ", Value: $" + item.getListPrice());
    }
    System.out.println("_______________________________________________");

    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("\n\n ~Fin~");

    try {
      Logger.SAVE();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    
    System.exit(0);

  }

  @Override
  public void nextState() {
    exitState();
  }

  @Override
  public boolean hasTask() {
    return false;
  }

  @Override
  public EmployeeTask getTask() {
    return null;
  }

  @Override
  public EmployeeTask getTask(Employee employee) {
    return null;
  }

  @Override
  public EventStatus getStatus() {
    return null;
  }

  @Override
  public EventStatus setStatus(EventStatus status) {
    return null;
  }

  public void update(Object message) {
  }
}
