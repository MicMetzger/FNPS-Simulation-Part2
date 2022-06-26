package main.java.com.events.task;

import static main.java.com.events.EventStatus.COMPLETE;
import static main.java.com.events.EventStatus.INCOMPLETE;
import static main.java.com.events.EventStatus.IN_PROGRESS;

import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.store.*;
import main.java.com.utilities.*;



/**
 * Start day.
 *
 * <p>Daily route starting point.
 */
public class StartDay implements State {
  Store       state;
  EventStatus status;

  public StartDay(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = IN_PROGRESS;

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
