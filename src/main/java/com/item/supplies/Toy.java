package main.java.com.item.supplies;

import static java.lang.Math.round;

import java.security.SecureRandom;
import main.java.com.item.pets.enums.AnimalType;
import main.java.com.item.supplies.enums.SupplyType;

public class Toy extends Supplies {

  // The animal
  private AnimalType animal;

  /**
   * Instantiates a new Leash
   *
   * @param animal the animal
   */
  public Toy(
      String name,
      double purchasePrice,
      double listPrice,
      double salePrice,
      int daySold,
      int dayArrived,
      AnimalType animal) {
    super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
    super.setName("Toy");
    this.animal = animal;
  }

  public Toy(AnimalType animal) {
    double newPurchasePrice = round(new SecureRandom().nextDouble(100));
    super.setPurchasePrice(newPurchasePrice);
    super.setListPrice(round(newPurchasePrice * (double) 2));
    super.setDayArrived(0);
    super.setName("Toy");
    this.animal = animal;
  }

  /** Default constructor */
  public Toy() {
    super();
	  super.supplyType = SupplyType.Toy;
  }
}
