package main.java.com.store;

public class Main {
  public static void main(String[] args) {
    Store storeSim = new Store();
    storeSim.initiateStaff();
    storeSim.initiateAnimals();
    storeSim.initiateSupplies();
    storeSim.initStates();
    storeSim.goNewDay();
  }
}
