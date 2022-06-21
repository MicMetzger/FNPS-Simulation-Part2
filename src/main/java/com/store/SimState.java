package main.java.com.store;

import main.java.com.Utilities;
import main.java.com.item.Item;
import main.java.com.item.Pet;
import main.java.com.individuals.Watcher;

import java.util.ArrayList;
import java.util.List;

public class SimState {
  // Package level access, static, state control variables
  static State newDay,
      startDay,
      endDay,
      processDelivery,
      feedAnimals,
      visitBank,
      checkRegister,
      doInventory,
      trainAnimals,
      openStore,
      cleanStore,
      goEndSimulation;
  static State currentState;
  static State endState;
  static State previousState;
  static List<State> stateList;
  static boolean RUNNING;
  Store store;

  public SimState(Store sim) {
    stateList = new ArrayList<State>();
    store = sim;
    newDay = new NewDay(this);
    startDay = new StartDay(this);
    endDay = new EndDay(this);
    feedAnimals = new FeedAnimals(this);
    visitBank = new VisitBank(this);
    doInventory = new DoInventory(this);
    processDelivery = new ProcessDelivery(this);
    cleanStore = new CleanStore(this);
    trainAnimals = new TrainAnimals(this);
    openStore = new OpenStore(this);
    goEndSimulation = new GoEndSimulation(this);
    // RUNNING = true;

    stateList.add(startDay);
    stateList.add(endDay);
    stateList.add(feedAnimals);
    stateList.add(visitBank);
    stateList.add(checkRegister);
    stateList.add(doInventory);
    stateList.add(openStore);
    stateList.add(cleanStore);
    stateList.add(goEndSimulation);
    goNewDay();
  }

  public void setStoreState(State state) {
    previousState = currentState;
    currentState = state;
  }

  public void goNewDay() {
    currentState = newDay;
    currentState.enterState();
  }

  public State goStartDay() {
    return startDay;
  }

  public State goProcessDelivery() {
    return processDelivery;
  }

  public State goCheckRegister() {
    return checkRegister;
  }

  public State goVisitBankState() {
    previousState = currentState;
    return visitBank;
  }

  public State goFeedAnimals() {
    return feedAnimals;
  }

  public State goDoInventory() {
    return doInventory;
  }

  public State goTrainAnimals() { return trainAnimals; }

  public State goOpenStore() {
    return openStore;
  }

  public State goCleanStore() {
    return cleanStore;
  }

  public State goEndDay() {
    return endDay;
  }

  public State goEndSimulation() {
    return goEndSimulation;
  }

  public void goEnterState() {
    currentState.enterState();
  }

  // public void update() {stateList.forEach(state -> state.update(this));}

}

class NewDay implements State, Watcher {
  SimState simState;

  NewDay(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n**************************************************");
    if (simState.store.day == 30) {
      simState.setStoreState(simState.goEndSimulation());
      exitState();
    }

    simState.store.day++;
    simState.store.selectStaff();
    System.out.println("Day: " + simState.store.day);
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("**************************************************\n");
    Utilities.gapTime();
    simState.goEnterState();
  }

  @Override
  public void nextState() {
    simState.setStoreState(simState.goStartDay());
    exitState();
  }

  @Override
  public void update(Object state) {}
}

/**
 * Start day.
 *
 * <p>Daily route starting point.
 */
class StartDay implements State, Watcher {
  SimState simState;

  public StartDay(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n#################################################");
    if (!simState.store.checkRegister()) {
      System.out.println("Register cash is low... ");
      simState.setStoreState(simState.goVisitBankState());
      simState.goEnterState();
    } else {
      System.out.println("Cash is sufficient.");
    }

    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.goEnterState();

    // TODO: update information and report. Afterwards, call nextState()
  }

  @Override
  public void nextState() {
    // simState.update();
    simState.setStoreState(simState.goProcessDelivery());
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class ProcessDelivery implements State, Watcher {

  SimState simState;

  public ProcessDelivery(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentClerk.processDeliveries();
    simState.store.updateMailBox();
    simState.store.updateInventory();
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.goEnterState();
  }

  @Override
  public void nextState() {
    System.out.println("The employee returns to finish his other activities.");
    simState.setStoreState(simState.goFeedAnimals());
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class FeedAnimals implements State, Watcher {
  SimState simState;

  public FeedAnimals(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentTrainer.feedAnimals();
    simState.store.updateInventory();
    simState.store.updateSickAnimal();
    // simState.store.updateCash();
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.goEnterState();
  }

  @Override
  public void nextState() {
    simState.setStoreState(simState.goDoInventory());
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class VisitBank implements State, Watcher {
  SimState simState;

  public VisitBank(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    simState.store.goToBank();
    nextState();
  }

  @Override
  public void exitState() {
    // simState.update();

  }

  @Override
  public void nextState() {
    simState.setStoreState(SimState.previousState);
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class DoInventory implements State, Watcher {
  SimState simState;

  public DoInventory(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentClerk.doInventory();
    simState.store.updateCash();
    simState.store.updateInventory();
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.goEnterState();

    // TODO: update information and report. Afterwards, call nextState()
  }

  @Override
  public void nextState() {
    simState.setStoreState(simState.goTrainAnimals());
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class TrainAnimals implements State {
  TrainAnimals(SimState simState) {
    this.simState = simState;
  }
  SimState simState;
  @Override
  public void enterState() {
    System.out.println("\n########TRAINING##########################################");
    // TODO: simState.store.currentTrainer.startTraining(); for each animal
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("\n##################################################");
    Utilities.gapTime();
    simState.goEnterState();
  }

  @Override
  public void nextState() {
    simState.setStoreState(simState.goOpenStore());
    exitState();
  }
}

class OpenStore implements State, Watcher {
  SimState simState;

  public OpenStore(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.openStore();
    simState.store.updateCash();
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.goEnterState();
  }

  @Override
  public void nextState() {
    simState.setStoreState(simState.goCleanStore());
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class CleanStore implements State, Watcher {
  SimState simState;

  public CleanStore(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentClerk.cleanStore();
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.setStoreState(simState.goEndDay());
    simState.goEnterState();
  }

  @Override
  public void nextState() {
    simState.store.updateInventory();
    simState.store.updateSickAnimal();
    exitState();
  }

  @Override
  public void update(Object message) {}
}

/**
 * End day. Completion of daily route.
 *
 * <p>Clean-up and preparation for sequence restart.
 */
class EndDay implements State, Watcher {
  SimState simState;

  public EndDay(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    System.out.println("The workday comes to an end...");
    // TODO: 4
    // empty register and store cash in Store
    simState.store.updateCash();
    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("##################################################\n");
    Utilities.gapTime();
    simState.goNewDay();
  }

  @Override
  public void nextState() {

    // simState.setStoreState(simState.goEndDay());
    exitState();
  }

  @Override
  public void update(Object message) {}
}

class GoEndSimulation implements State, Watcher {
  SimState simState;

  GoEndSimulation(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n\n_______________ STATS _______________");
    System.out.println("Total Cash: $" + simState.store.cash);
    System.out.println("Total withdrawal: $" + simState.store.bankWithdrawal);
    System.out.println("_______________________________________________");

    System.out.println("\n\n_______________ Items Sold _______________");
    for (Item item : simState.store.getSoldItems()) {
      System.out.println(
          item.getName() + " $" + item.getSalePrice() + ", Sold on: DAY " + item.getDaySold());
    }
    System.out.println("_______________________________________________");

    System.out.println("\n\n_______________ Remaining Items _______________");
    for (Item item : simState.store.getInventory()) {
      System.out.println(item.getName() + ", Value: $" + item.getListPrice());
    }
    System.out.println("_______________________________________________");

    System.out.println("\n\n_______________ Remaining Sick Animals _______________");
    for (Pet item : simState.store.getSick()) {
      System.out.println(item.getBreed() + ", Value: $" + item.getListPrice());
    }
    System.out.println("_______________________________________________");

    nextState();
  }

  @Override
  public void exitState() {
    System.out.println("\n\n ~Fin~");
    System.exit(0);
  }

  @Override
  public void nextState() {
    exitState();
  }

  @Override
  public void update(Object message) {}
}
