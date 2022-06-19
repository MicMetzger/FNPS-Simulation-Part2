package main.java.com.store;

import main.java.com.Utilities;
import main.java.com.item.Item;
import main.java.com.item.pet.Pet;

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

class NewDay implements State {
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
}

/**
 * Start day.
 *
 * <p>Daily route starting point.
 */
class StartDay implements State {
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
}

class ProcessDelivery implements State {

  SimState simState;

  public ProcessDelivery(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentStaff.processDeliveries();
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
}

class FeedAnimals implements State {
  SimState simState;

  public FeedAnimals(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentStaff.feedAnimals();
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
}


class VisitBank implements State {
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
}

class DoInventory implements State {
  SimState simState;

  public DoInventory(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentStaff.doInventory();
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
    simState.setStoreState(simState.goOpenStore());
    exitState();
  }
}

class OpenStore implements State {
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
}

class CleanStore implements State {
  SimState simState;

  public CleanStore(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n##################################################");
    simState.store.currentStaff.cleanStore();
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
}

/**
 * End day. Completion of daily route.
 *
 * <p>Clean-up and preparation for sequence restart.
 */
class EndDay implements State {
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
}

class GoEndSimulation implements State {
  SimState simState;

  GoEndSimulation(SimState simState) {
    this.simState = simState;
  }

  @Override
  public void enterState() {
    System.out.println("\n\n_______________ STATS _______________");
    System.out.println("Total Cash: $" + simState.store.cash);
    System.out.println("Total withdrawal: $" + simState.store.bankWithdrawal);
    System.out.println("_____________________________________________");

    System.out.println("\n\n_______________ Items Sold _______________");
    for (Item item : simState.store.getSoldItems()) {
      System.out.println(
          item.getName() + " $" + item.getSalePrice() + ", Sold on: DAY " + item.getDaySold());
    }
    System.out.println("_____________________________________________");

    System.out.println("\n\n_______________ Remaining Items _______________");
    for (Item item : simState.store.getInventory()) {
      System.out.println(item.getName() + ", Value: $" + item.getListPrice());
    }
    System.out.println("_____________________________________________");

    System.out.println("\n\n_______________ Remaining Sick Animals _______________");
    for (Pet item : simState.store.getSick()) {
      System.out.println(item.getBreed() + ", Value: $" + item.getListPrice());
    }
    System.out.println("_____________________________________________");

    nextState();
  }

  @Override
  public void exitState() {
    System.exit(0);
  }

  @Override
  public void nextState() {
    exitState();
  }
}
