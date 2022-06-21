package main.java.com.store;

import main.java.com.item.Item;
import main.java.com.item.Pet;
import main.java.com.item.pets.*;
import main.java.com.item.pets.enums.Animal;
import main.java.com.item.pets.enums.AnimalType;
import main.java.com.item.pets.enums.Color;
import main.java.com.item.supplies.*;
import main.java.com.item.supplies.enums.Type;
import main.java.com.staff.Clerk;
import main.java.com.staff.Employee;
import main.java.com.staff.Trainer;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.*;

public class Store {
  // The store's Inventory.
  ArrayList<Item> inventory;
  ArrayList<Pet> sick;
  ArrayList<Customer> customers;
  ArrayList<DeliveryPackage> mailBox;
  // The store's staff
  ArrayList<Employee> clerks;

  ArrayList<Employee> trainers;
  ArrayList<Item> soldItems;
  Employee currentClerk;
  Employee currentTrainer;
  // Money + day management
  double bankWithdrawal;
  double cash;
  int day;
  // Data helpers
  protected final ArrayList<String> colors =
      new ArrayList<String>(Arrays.asList("Black", "Brown", "White", "Gray", "Red"));
  protected final boolean[] randomSelectionbool = {true, false};
  DecimalFormat sizeFormat = new DecimalFormat("#####.00");

  /**
   * Instantiates a new Store. Main entry point.
   *
   * <p>Default constructor
   */
  public Store() {
    clerks = new ArrayList<Employee>();
    trainers = new ArrayList<Employee>();
    customers = new ArrayList<Customer>();
    inventory = new ArrayList<Item>();
    sick = new ArrayList<Pet>();
    mailBox = new ArrayList<DeliveryPackage>();
    soldItems = new ArrayList<Item>();
    bankWithdrawal = 0;
    cash = 0;
    day = 0;
    initItemsAndStaff();
    new SimState(this);
  }

  /** Initiate starting objects. */
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
            colors.get(new Random().nextInt(colors.size())),
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
            colors.get(new Random().nextInt(colors.size())),
            randomSelectionbool[new Random().nextInt(1)]));

	  // (size) / (breed, age, health)
	  inventory.add(new Snake(Double.parseDouble(sizeFormat.format(new Random().nextDouble(8)))));

    inventory.add(
        new Food(
            new Random().nextInt(100),
            AnimalType.values()[new Random().nextInt(AnimalType.values().length)],
            Type.values()[new Random().nextInt(Type.values().length)]));
    inventory.add(new CatLitter(new Random().nextInt(100)));
    inventory.add(new Toy(AnimalType.values()[new Random().nextInt(AnimalType.values().length)]));
    inventory.add(new Leash(AnimalType.values()[new Random().nextInt(AnimalType.values().length)]));
    inventory.add(new Treat(AnimalType.values()[new Random().nextInt(AnimalType.values().length)]));
    // inventory.add()

  }

<<<<<<< HEAD
  /**
   * TODO: each of the woker types need to be selected as currentStuff, so have an array to hold current Stuffs
   * Select staff to main store for this day.
   */

  void selectStaff() {
    int num = new Random().nextInt(4);
    currentStaff = staff.get(num);

    if (currentStaff.getWorkDays() <= 3) {
      staff.forEach(
        employee -> {
          if (employee != currentStaff) {
            employee.dayoff();
          }
        });
    } else {
      staff.forEach(
        employee -> {
          if (employee != currentStaff) {
            // currentStaff.dayoff();
            currentStaff = employee;
            currentStaff.incWorkDays();
            currentStaff.arrival();
            staff.forEach(
              restream -> {
                if (restream != currentStaff) {
                  restream.dayoff();
                }
                return;
              });
          }
        });
=======

  Employee pickAvailableStuff(ArrayList<Employee> staffList) {
    SecureRandom rand = new SecureRandom();
    Employee potentialStaff = staffList.get(rand.nextInt(3));
    if(potentialStaff.getWorkDays() <= 3) {
      /* Passing store info to the staff */
      potentialStaff.setInventory(this.inventory);
      potentialStaff.setSickPet(this.sick);
      potentialStaff.setMailBox(this.mailBox);
      potentialStaff.setCash(this.cash);
      potentialStaff.incWorkDays();
      potentialStaff.arrival();
      // update work days of the staffs
      for(Employee staff:staffList) {
        if (staff != potentialStaff) {
          staff.dayoff();
        } else {
          potentialStaff.incWorkDays();
        }
      }
      return potentialStaff;
    } else {
      // randomly selected staff unable to work
      return pickAvailableStuff(staffList);
>>>>>>> main
    }
  }

  /** Select staff to man store for this day. */
  void selectStaff() {
    currentClerk = pickAvailableStuff(clerks);
    currentTrainer = pickAvailableStuff(trainers);
  }



  public void openStore() {
    int count = attractCustomers(new SecureRandom().nextInt(3, 10));
    System.out.println(
        currentClerk.getName()
            + " opens the store. \nCurrent inventory: "
            + inventory.size()
            + " item(s)\nRegister: "
            + cash);
    System.out.println(count + " potential customers enter the store...");
    // boolean buyAtListPrice = new SecureRandom().nextInt(100) < 50;

    customers.forEach(
        customer -> {
          boolean selecting = customer.inspectInventory(inventory);

          if (selecting) {
            inventory.remove(customer.obj);
            System.out.println("[+] The customer has made a selection!");
            System.out.println(
                "[+] The customer purchases "
                    + customer.obj.getName()
                    + " at $"
                    + customer.getPurchasePrice()
                    + (customer.discount ? " after a 10% discount" : ""));
            cash += customer.getPurchasePrice();
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

  public void goToBank() {
    currentClerk.goToBank();
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
}
