package test.java.com.store;

import java.util.*;
import main.java.com.item.*;
import main.java.com.item.pets.*;
import main.java.com.store.*;
import org.junit.jupiter.api.*;



class StoreTest {

  private final Store storeTest;


  StoreTest() {
    this.storeTest = new Store();
  }


  /**
   * Test that inventory pets are correctly added to the inventory, and are/maintain the expected type instance type (Pet)
   */
  @Test
  private void testInitPetsTest() {
    List<Item> inventory = new ArrayList<Item>();
    inventory.add(new Bird());
    inventory.add(new Cat());
    inventory.add(new Dog());
    inventory.add(new Ferret());
    inventory.add(new Snake());
    storeTest.getInventory().addAll(inventory);
    Assertions.assertEquals(5, storeTest.getInventory().size());
    storeTest.getInventory().forEach(item -> Assertions.assertTrue(item instanceof Pet));
  }


  /**
   * Test to start simulation sequence, and increment the day.
   */
  @Test
  public void testGoNewDay() {
    storeTest.goNewDay();
    if (storeTest.getDay() == 1) {
      Assertions.assertTrue(true);
    } else {
      Assertions.assertTrue(false);
    }
    // tearDown();
  }

  @BeforeEach
  public void setUp() {
    new StoreTest();
    storeTest.initiateSupplies();
    storeTest.initStates();
  }

  @AfterEach
  public void tearDown() {
    storeTest.getInventory().clear();
    storeTest.getEmployees().clear();
    storeTest.getStates().clear();
    storeTest.resetDay();
  }
}
