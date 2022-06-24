package main.java.com.item.supplies;

import main.java.com.item.pets.enums.*;

public class Leash extends Supplies {

  // The animal
  private AnimalType animal;

  /**
   * Instantiates a new Leash
   *
   * @param animal the animal
   */
  public Leash(AnimalType animal) {
    super();
    super.setDayArrived(0);
    super.setName("Leash");
    this.animal = animal;
  }

  public Leash(String animal) {
    this.animal = AnimalType.valueOf(animal);
  }

  /** Default constructor */
  public Leash(
      String name,
      double purchasePrice,
      double listPrice,
      double salePrice,
      int dayArrived,
      int daySold,
      AnimalType animal) {
    super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
    this.animal = animal;
  }

  /**
   * Gets the animal
   *
   * @return the animal
   */
  public AnimalType getAnimal() {
    return animal;
  }

  /**
   * Sets the animal
   *
   * @param animal the animal
   */
  public void setAnimal(String animal) {
    this.animal = AnimalType.valueOf(animal);
  }
}
