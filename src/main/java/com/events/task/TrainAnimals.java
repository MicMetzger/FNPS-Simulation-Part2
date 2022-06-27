package main.java.com.events.task;

import static main.java.com.events.EventStatus.*;

import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.store.*;
import utilities.*;



public class TrainAnimals implements State {
  Store        state;
  EmployeeTask task;
  EventStatus  status;

  public TrainAnimals(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = IN_PROGRESS;

    System.out.println("\n##################################################");
    ((Trainer) state.currentTrainer).startTraining();
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("\n##################################################");
    Utilities.gapTime();
    state.goEnterState();
  }

  @Override
  public void nextState() {
    state.setStoreState(state.goOpenStore());
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
}