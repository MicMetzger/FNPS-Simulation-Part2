package main.java.com.store;


import static main.java.com.events.EventStatus.*;

import main.java.com.*;
import main.java.com.events.*;
import main.java.com.events.task.*;
import main.java.com.individuals.*;
import main.java.com.item.*;



class NewDay implements State {
  Store       state;
  EventStatus status;

  NewDay(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n**************************************************");
    if (state.day == 30) {
      state.goEndSimulation();
      exitState();
    }

    state.day++;
    state.selectStaff();
    System.out.println("Day: " + state.day);
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("**************************************************\n");
    Utilities.gapTime();
    state.goEnterState();
  }

  @Override
  public void nextState() {
    state.setStoreState(state.goStartDay());
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

  public void update(Object state) {
  }
}



/**
 * Start day.
 *
 * <p>Daily route starting point.
 */
class StartDay implements State {
  Store       state;
  EventStatus status;

  public StartDay(Store store) {
    this.state  = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n#################################################");
    if (!state.checkRegister()) {
      System.out.println("Register cash is low... ");
      state.setStoreState(state.goVisitBankState());
      state.goEnterState();
    } else {
      System.out.println("Cash is sufficient.");
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

  public void update(Object message) {
  }
}



class ProcessDelivery implements State {
  Store state;

  EventStatus status;

  public ProcessDelivery(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n##################################################");
    state.currentClerk.processDeliveries();
    state.updateMailBox();
    state.updateInventory();
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("##################################################\n");
    Utilities.gapTime();
    state.goEnterState();
  }

  @Override
  public void nextState() {
    System.out.println("The employee returns to finish his other activities.");
    state.setStoreState(state.goFeedAnimals());
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

  public void update(Object message) {
  }
}



class FeedAnimals implements State {
  Store state;

  EventStatus status;

  public FeedAnimals(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n##################################################");
    state.currentTrainer.feedAnimals();
    state.updateInventory();
    state.updateSickAnimal();
    // updateCash();
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("##################################################\n");
    Utilities.gapTime();
    state.goEnterState();
  }

  @Override
  public void nextState() {
    state.setStoreState(state.goDoInventory());
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

  public void update(Object message) {
  }
}



class DoInventory implements State {
  Store state;

  EventStatus status;

  public DoInventory(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n##################################################");
    state.currentClerk.doInventory();
    state.updateCash();
    state.updateInventory();
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
    state.setStoreState(state.goTrainAnimals());
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

  public void update(Object message) {
  }
}



class TrainAnimals implements State {
  Store state;

  EventStatus status;

  TrainAnimals(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

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
}



class OpenStore implements State {
  Store state;

  EventStatus status;

  public OpenStore(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n##################################################");
    state.openStore();
    state.updateCash();
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("##################################################\n");
    Utilities.gapTime();
    state.goEnterState();
  }

  @Override
  public void nextState() {
    state.setStoreState(state.goCleanStore());
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

  public void update(Object message) {
  }
}



class CleanStore implements State {
  Store state;

  EventStatus status;

  public CleanStore(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n##################################################");
    state.currentClerk.cleanStore();
    nextState();
  }

  @Override
  public void exitState() {
    this.status = COMPLETE;

    System.out.println("##################################################\n");
    Utilities.gapTime();
    state.setStoreState(state.goEndDay());
    state.goEnterState();
  }

  @Override
  public void nextState() {
    state.updateInventory();
    state.updateSickAnimal();
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

  public void update(Object message) {
  }
}



/**
 * End day. Completion of daily route.
 *
 * <p>Clean-up and preparation for sequence restart.
 */
class EndDay implements State {
  Store state;

  EventStatus status;

  public EndDay(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

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

  public void update(Object message) {
  }
}



class GoEndSimulation implements State {
  Store       state;
  EventStatus status;

  GoEndSimulation(Store store) {
    this.state = store;
    this.status = INCOMPLETE;
  }

  @Override
  public void enterState() {
    this.status = RUNNING;

    System.out.println("\n\n_______________ STATS _______________");
    System.out.println("Total Cash: $" + state.cash);
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

  public void update(Object message) {
  }
}
