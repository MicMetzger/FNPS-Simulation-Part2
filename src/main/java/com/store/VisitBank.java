package main.java.com.store;

import static main.java.com.events.EventStatus.*;

import main.java.com.events.*;
import main.java.com.events.task.*;
import main.java.com.individuals.*;
import main.java.com.individuals.Employee.*;



public class VisitBank implements State {
  Store storeState;
  EventStatus status;
  
  VisitBank(Store store) {
    this.storeState = store;
    this.status = INCOMPLETE;

  }

  // public VisitBank(Employee employee, Store store) {
  class Banking extends EmployeeTask {
    Banking(Employee employee, Store store) {
      super(TaskType.TASK_BANKING, INCOMPLETE, employee);
      instance = store;
    }


    @Override
    public void run() {
      super.statusChange(RUNNING);
      System.out.println("\n##################################################");
      storeState.goToBank(getEmployee());
      end();
    }

    public void end() {
      super.statusChange(COMPLETE);
      getEmployee().setState(EmployeeState.IDLE);
    }
  }
  
  @Override
  public void enterState() {
    storeState.notifyObservers(this);
    exitState();
  }

  @Override
  public void exitState() {

  }

  @Override
  public void nextState() {

  }

  @Override
  public boolean hasTask() {
    return true;
  }

  @Override
  public EmployeeTask getTask() {
    return new Banking(null, storeState);
  }

  public EmployeeTask getTask(Employee employee) {
    return new Banking(employee, storeState);
  }
}
