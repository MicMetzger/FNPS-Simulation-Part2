package main.java.com.events.task;

import static main.java.com.events.EventStatus.*;

import java.util.*;
import main.java.com.Logging.*;
import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.item.*;
import main.java.com.item.supplies.*;
import main.java.com.store.*;
import main.java.com.utilities.*;



/**
 * Start day.
 *
 * <p>Daily route starting point.
 */
public class StartDay implements State {
  Store          state;
  EventStatus    status;
  List<Employee> employees;
  List<Pet>      pets;
  List<Supplies> supplies;

  public StartDay(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = IN_PROGRESS;

    employees = state.selectStaff();
    pets      = state.getAnimals();
    supplies  = state.getSupplies();
    
    System.out.println("\n#################################################");
    if (!state.checkRegister()) {
      System.out.println("Register cash is low... ");
      state.setStoreState(state.goVisitBankState());
      state.goEnterState();
    } else {
      System.out.println("Cash is sufficient");
    }

    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;
    Logger.LOG(EventLog.startDayEvent(employees, employees.size(), pets, pets.size(), supplies, supplies.size()));
    System.out.println("##################################################\n");
    Utilities.gapTime();
    state.goEnterState();

    // TODO: update information and report. Afterwards, call nextState()
  }

  @Override
  public void nextState() {
    // update();
    state.setStoreState(state.goProcessDelivery());
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
