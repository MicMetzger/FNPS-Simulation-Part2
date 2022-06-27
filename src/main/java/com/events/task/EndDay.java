package main.java.com.events.task;

import static main.java.com.events.EventStatus.*;

import java.util.*;
import main.java.com.Logging.*;
import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.store.*;
import utilities.*;



/**
 * End day. Completion of daily route.
 *
 * <p>Clean-up and preparation for sequence restart.
 */
public class EndDay implements State {
  Store                       state;
  EmployeeTask                task;
  EventStatus                 status;
  int                         Day;
  List<Employee>              employeeLog;
  List<Pair<Integer, Double>> employeeEarnings;
  
  public EndDay(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = IN_PROGRESS;

    employeeLog      = new ArrayList<Employee>(state.getEmployees());
    employeeEarnings = new ArrayList<Pair<Integer, Double>>();
    employeeLog.forEach(employee -> employeeEarnings.add(new Pair<Integer, Double>(employee.getSold(), employee.getEarning())));
    Logger.LOG(EventLog.tracking(employeeLog, employeeEarnings, state.day));
    // Logger.LOG(EventLog.tracking(employeeLog, employeeEarnings, state.day));

    System.out.println("\n##################################################");
    System.out.println("The workday comes to an end...");

    // TODO: 4
    // empty register and store cash in Store
    state.updateCash();
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("##################################################\n");
    Utilities.gapTime();
    state.goNewDay();
  }

  @Override
  public void nextState() {

    // stateMachine.setStoreState(stateMachine.goEndDay());
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
