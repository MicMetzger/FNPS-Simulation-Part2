package main.java.com.events.task;

import static main.java.com.events.EventStatus.COMPLETE;
import static main.java.com.events.EventStatus.INCOMPLETE;
import static main.java.com.events.EventStatus.IN_PROGRESS;

import main.java.com.Logging.*;
import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.store.*;
import main.java.com.utilities.*;



/**
 * End day. Completion of daily route.
 *
 * <p>Clean-up and preparation for sequence restart.
 */
public class EndDay implements State {
  Store        state;
  EmployeeTask task;
  EventStatus  status;

  public EndDay(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = IN_PROGRESS;

    System.out.println("\n##################################################");
    System.out.println("The workday comes to an end...");
    Logger.LOG("\n\n");

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
