package main.java.com.store;

import static main.java.com.Builders.*;

import java.security.*;
import java.util.*;
import main.java.com.*;
import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.item.*;
import main.java.com.item.addOns.*;
import main.java.com.item.pets.*;
import main.java.com.item.pets.enums.*;
import main.java.com.item.supplies.*;
import main.java.com.item.supplies.enums.*;



public class Store implements EventObservable {
  private       Logger logger  = Logger.getLogger(Store.class);
  private final Object MONITOR = new Object();
  static        State  newDay,
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
      goEndSimulation,
      currentState,
      endState,
      previousState;
  static List<State> stateList;

  // The store's Inventory.
  ArrayList<Item>            inventory;
  ArrayList<Pet>             sick;
  ArrayList<Customer>        customers;
  ArrayList<DeliveryPackage> mailBox;
  // The store's staff
  ArrayList<Employee>        clerks;
  ArrayList<Employee>        trainers;
  ArrayList<EventObserver>   observers;
  ArrayList<Item>            soldItems;
  Employee                   currentClerk;
  Employee                   currentTrainer;
  // Money + day management
  double                     bankWithdrawal;
  double                     cash;
  int                        day;



  private static final class InstanceHolder {
    private static final Store instance = new Store();
  }

  public static Store getInstance() {
    return InstanceHolder.instance;
  }

  /**
   * Instantiates a new Store. Main entry point.
   *
   * <p>Default constructor
   */
  public Store() {
    clerks         = new ArrayList<Employee>();
    trainers       = new ArrayList<Employee>();
    customers      = new ArrayList<Customer>();
    inventory      = new ArrayList<Item>();
    sick           = new ArrayList<Pet>();
    mailBox        = new ArrayList<DeliveryPackage>();
    soldItems      = new ArrayList<Item>();
    bankWithdrawal = 0;
    cash           = 0;
    day            = 0;

    initItemsAndStaff();
    initStates();
    goNewDay();
  }

  /**
   * Initiate starting objects.
   */
  public void initItemsAndStaff() {
    clerks.add(new Clerk());
    clerks.add(new Clerk());
    clerks.add(new Clerk());
    trainers.add(new Trainer("Haphazard"));
    trainers.add(new Trainer("Negative"));
    trainers.add(new Trainer("Positive"));
   

    // (size, color, broken, purebred) / (breed, age, health)
    inventory.add(
        new Dog(
            Double.parseDouble(sizeFormat.format(new SecureRandom().nextDouble(50.0))),
            Color.values()[
                new Random()
                    .nextInt(
                        Color.values().length)] /*colors.get(new Random().nextInt(colors.size()))*/,
            randomSelectionbool[new Random().nextInt(1)],
            randomSelectionbool[new Random().nextInt(1)]));

    // (color, broken, purebred) / (breed, age, health)
    inventory.add(
        new Cat(
            COLORS.get(new Random().nextInt(COLORS.size())),
            randomSelectionbool[new Random().nextInt(1)],
            randomSelectionbool[new Random().nextInt(1)]));

    // (size, mimicry, exotic, papers) / (breed, age, health)
    inventory.add(
        new Bird(
            Double.parseDouble(sizeFormat.format(new Random().nextDouble(8))),
            randomSelectionbool[new Random().nextInt(1)],
            randomSelectionbool[new Random().nextInt(1)],
            randomSelectionbool[new Random().nextInt(1)]));

    // (color, broken, purebred) / (breed, age, health)
    inventory.add(
        new Ferret(
            Color.values()[
                new Random()
                    .nextInt(
                        Color.values().length)],
            randomSelectionbool[new Random().nextInt(1)]));

    // (size) / (breed, age, health)
    inventory.add(new Snake(Double.parseDouble(sizeFormat.format(new Random().nextDouble(8)))));

    inventory.add(
        new Food(
            new Random().nextInt(100),
            AnimalType.values()[new Random().nextInt(AnimalType.values().length)],
            Type.values()[new Random().nextInt(Type.values().length)]));
    inventory.add(new CatLitter(new Random().nextInt(100)));
    //    inventory.add(new Toy(AnimalType.values()[new Random().nextInt(AnimalType.values().length)]));
    inventory.add(new Leash(AnimalType.values()[new Random().nextInt(AnimalType.values().length)]));
    inventory.add(new Treat(AnimalType.values()[new Random().nextInt(AnimalType.values().length)]));

  }

  private void initStates() {
    stateList       = new ArrayList<State>();
    newDay          = new NewDay(this);
    startDay        = new StartDay(this);
    endDay          = new EndDay(this);
    feedAnimals     = new FeedAnimals(this);
    visitBank       = new VisitBank(this);
    doInventory     = new DoInventory(this);
    processDelivery = new ProcessDelivery(this);
    cleanStore      = new CleanStore(this);
    trainAnimals    = new TrainAnimals(this);
    openStore       = new OpenStore(this);
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
  }


  Employee pickAvailableStaff(ArrayList<Employee> staffList) {
    SecureRandom rand           = new SecureRandom();
    boolean      isSick         = rand.nextInt(100) < 10;
    Employee     potentialStaff = staffList.get(rand.nextInt(3));
    if (potentialStaff.getWorkDays() <= 3) {
      if (isSick) {
        System.out.println(potentialStaff.getName() + " is feeling sick today. Selecting another staff...");
        return pickAvailableStaff(staffList);
      }
      /* Passing store info to the staff */
      potentialStaff.setInventory(this.inventory);
      potentialStaff.setSickPet(this.sick);
      potentialStaff.setMailBox(this.mailBox);
      potentialStaff.setCash(this.cash);
      potentialStaff.incWorkDays();
      potentialStaff.arrival();
      // update work days of the staffs
      for (Employee staff : staffList) {
        if (staff != potentialStaff) {
          staff.dayoff();
        } else {
          potentialStaff.incWorkDays();
        }
      }
      return potentialStaff;
    } else {
      // randomly selected staff unable to work
      return pickAvailableStaff(staffList);
    }
  }

  /**
   * Select staff to man store for this day.
   */
  public void selectStaff() {
    currentClerk = pickAvailableStaff(clerks);
    currentClerk.setACTIVE(true);
    currentClerk.setTask(null);

    currentTrainer = pickAvailableStaff(trainers);
    currentTrainer.setACTIVE(true);
    currentTrainer.setTask(null);
    
    addObserver(currentClerk);
    addObserver(currentTrainer);
  }

  /* Reference: http://en.wikipedia.org/wiki/Poisson_distribution#Generating_Poisson-distributed_random_variables */
  public int getPoissonValue(double mean) {
    SecureRandom r = new SecureRandom();
    double       L = Math.exp(-mean);
    int          k = 1;
    double       p = 1.0;
    do {
      p = p * r.nextDouble();
      ++k;
    } while (p > L);
    return k - 1;
  }

  public double addOnsHelper(ArrayList<String> addOns, int quantity, Item baseItem) {
    if (quantity < 0) {
      return baseItem.getSalePrice();
    }

    // adds decorators recursively until quantity is negative
    return switch (addOns.get(quantity)) {
      case "Insurance" -> addOnsHelper(addOns, quantity - 1, new Insurance(baseItem));
      case "Vet" -> addOnsHelper(addOns, quantity - 1, new VetCheckup(baseItem));
      case "Microchip" -> addOnsHelper(addOns, quantity - 1, new Microchip(baseItem));
      default -> 0;
    };

  }

  public double addRandomAddons(Item item) {
    ArrayList<String> addOns = new ArrayList<String>(Arrays.asList("Insurance", "Vet", "Microchip"));
    Collections.shuffle(addOns);
    return addOnsHelper(addOns, new SecureRandom().nextInt(3), item);
  }

  public void openStore() {
    // Poisson distribution
    int count = attractCustomers(getPoissonValue(3.0));
    System.out.println(
        currentClerk.getName()
        + " opens the store. \nCurrent inventory: "
        + inventory.size()
        + " item(s)\nRegister: "
        + cash);
    System.out.println(count + " potential customers enter the store...");

    customers.forEach(
        customer -> {
          boolean selecting = customer.inspectInventory(inventory);
          if (selecting) {
            inventory.remove(customer.obj);
            System.out.println("The customer has made a selection!");
            System.out.println(
                "[+] The customer purchases "
                + customer.obj.getName()
                + " at $"
                + customer.getPurchasePrice()
                + (customer.discount ? " after a 10% discount" : ""));
            if (customer.obj.isPet()) {
              double total = addRandomAddons(customer.obj);
              cash += total;
              System.out.println(" ++ $" + total);
            } else {
              System.out.println(" ++ $" + customer.getPurchasePrice());
              cash += customer.getPurchasePrice();
            }
            customer.obj.setDaySold(day);
            soldItems.add(customer.obj);
          }
        });
    currentClerk.setCash(cash);
    System.out.println("\nCurrent inventory: " + inventory.size() + " item(s)\nCash: " + cash);
  }

  private int attractCustomers(int count) {
    customers.clear();
    for (int i = 0; i < count; i++) {
      customers.add(new Customer());
    }

    return count;
  }

  public void updateInventory() {
    this.inventory = currentClerk.getInventory();
  }

  public void updateSickAnimal() {
    this.sick = currentClerk.getSickAnimal();
  }

  public void updateMailBox() {
    this.mailBox = currentClerk.getMailBox();
  }

  public void updateCash() {
    this.cash = currentClerk.getCash();
  }

  /**
   * the mailbox
   *
   * @return mailBox
   */
  public ArrayList<DeliveryPackage> getMailbox() {
    return this.mailBox;
  }

  public void goToBank(Employee employee) {
    employee.goToBank();
    addWithdrawal();
  }

  private void addWithdrawal() {
    System.out.println("$1000.00 was withdrawn from the bank.\n");
    cash += currentClerk.getCash();
    bankWithdrawal += 1000;
    System.out.println("Total withdrawal: " + bankWithdrawal);
    System.out.println("Total cash: " + cash);
  }

  public double getCash() {
    return cash;
  }

  public void addCash(double cash) {
    this.cash = Double.parseDouble(sizeFormat.format(this.cash += cash));
    if (cash < 200) {
      System.out.println("$" + cash + " was removed from the register.");
    } else {
      System.out.println("$" + cash + " was added to the register.");
    }
  }

  public boolean checkRegister() {
    currentClerk.checkRegister();
    return this.getCash() > 200;
  }

  public ArrayList<Item> getInventory() {
    return inventory;
  }

  public ArrayList<Pet> getSick() {
    return sick;
  }

  public ArrayList<Item> getSoldItems() {
    return this.soldItems;
  }

  public void timePasses() {
    day++;
    System.out.println("\nDay " + day);

  }

  public void closeStore() {
    System.out.println(
        currentClerk.getName()
        + " closes the store. \nCurrent inventory: "
        + inventory.size()
        + " item(s)\nRegister: "
        + cash);
    System.out.println("\nCurrent inventory: " + inventory.size() + " item(s)\nCash: " + cash);
  }


  public void setStoreState(State state) {
    previousState = currentState;
    currentState  = state;
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

  public State goTrainAnimals() {
    return trainAnimals;
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

  public void goEndSimulation() {
    currentState = goEndSimulation;
  }

  public void goEnterState() {
    currentState.enterState();
  }

  /**
   * Add observer.
   *
   * @param observer the observer to add if it is not already added.
   */
  @Override
  public void addObserver(EventObserver observer) {
    //    assert observer != null;
    if (observer != null) {
      synchronized (this) {
        if (observers == null) {
          observers = new ArrayList<EventObserver>(1);
        }
        if (!observers.contains(observer)) {
          observers.add(observer);
        }
      }
    } else {
      throw new IllegalArgumentException("Observer cannot be null.");
    }

  }


  /**
   * Remove observer.
   *
   * @param observer the observer to be removed if it is registered.
   */
  @Override
  public void removeObserver(EventObserver observer) {
    if (observer != null) {
      synchronized (this) {
        if (observers != null && observers.remove(observer) && observers.isEmpty()) {
          observers = new ArrayList<>(1);
        }
      }
    }

  }

  /**
   * Notify observers.
   *
   * @param argument the Runnable to run
   */
  @Override
  public void notifyObservers(Object argument) {
    ArrayList<EventObserver> observersLocal;

    if (observers != null) {
      synchronized (this) {
        observersLocal = new ArrayList<EventObserver>(observers);
      }
      for (EventObserver observer : observersLocal) {
        observer.update(this, argument);
      }
    }

  }
}
