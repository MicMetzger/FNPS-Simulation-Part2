package main.java.com.item.pets;

import main.java.com.item.*;
import main.java.com.item.pets.enums.*;

/** The type Dog. */
public class Dog extends Pet {
  /** The Size. */
  public double size;
  /** The Color. */
  public Color color;
  /** The Housebroken. */
  public boolean housebroken;
  /** The Purebred. */
  public boolean purebred;

  /**
   * Instantiates a new Dog.
   *
   * @param name the name
   * @param dayArrived the day arrived
   * @param daySold the day sold
   * @param purchasePrice the purchase price
   * @param listPrice the list price
   * @param salePrice the sale price
   * @param animal the animaltype and the breed
   * @param age the age
   * @param healthy the healthy
   * @param size the size
   * @param color the color
   * @param housebroken the housebroken
   * @param purebred the purebred
   */
  public Dog(
      String name,
      int dayArrived,
      int daySold,
      double purchasePrice,
      double listPrice,
      double salePrice,
      Animal animal,
      int age,
      boolean healthy,
      double size,
      Color color,
      boolean housebroken,
      boolean purebred) {
    super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, animal, age, healthy);
    this.size = size;
    this.color = color;
    this.housebroken = housebroken;
    this.purebred = purebred;
  }

  /**
   * Instantiates a new Dog.
   *
   * @param animal the animaltype and the breed
   * @param age the age
   * @param healthy the healthy
   * @param size the size
   * @param color the color
   * @param housebroken the housebroken
   * @param purebred the purebred
   */
  public Dog(
      Animal animal,
      int age,
      boolean healthy,
      double size,
      Color color,
      boolean housebroken,
      boolean purebred) {
    super(animal, age, healthy);
    this.size = size;
    this.color = color;
    this.housebroken = housebroken;
    this.purebred = purebred;
  }

  /**
   * Instantiates a new Dog.
   *
   * @param size the size
   * @param color the color
   * @param housebroken the housebroken
   * @param purebred the purebred
   */
  public Dog(double size, Color color, boolean housebroken, boolean purebred) {
    super();
    super.setDayArrived(0);
    super.setName("Dog");
    this.size = size;
    this.color = color;
    this.housebroken = housebroken;
    this.purebred = purebred;
  }

  public Dog(
      String name,
      int dayArrived,
      int daySold,
      double purchasePrice,
      double listPrice,
      double salePrice,
      Animal animal,
      int age,
      boolean healthy) {
    super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, animal, age, healthy);
    super.setName("Dog");
  }

  /**
   * Instantiates a new Dog.
   *
   * @param animal the animal and it's breed
   * @param age the age
   * @param healthy the healthy
   */
  public Dog(Animal animal, int age, boolean healthy) {
    super(animal, age, healthy);
    super.setName("Dog");
  }

  /** Instantiates a new Dog. */
  public Dog() {
    super();
    super.setDayArrived(0);
    super.setName("Dog");
  }

  /**
   * Sets list price.
   *
   * @param listPrice the list price
   */
  @Override
  public void setListPrice(double listPrice) {
    super.setListPrice(listPrice);
  }

  @Override
  public int setHealthy(boolean healthy) {
    super.setHealthy(healthy);
    return 0;
  }

  @Override
  public String toString() {
    return "Dog{"
        + "size="
        + size
        + ", color='"
        + color
        + '\''
        + ", housebroken="
        + housebroken
        + ", purebred="
        + purebred
        + "} "
        + super.toString();
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public double getSize() {
    return size;
  }

  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(double size) {
    this.size = size;
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Sets color.
   *
   * @param color the color
   */
  public void setColor(String color) {
    this.color = Color.valueOf(color);
  }

  /**
   * Is housebroken boolean.
   *
   * @return the boolean determining if the dog is housebroken or not.
   */
  public boolean isHousebroken() {
    return housebroken;
  }

  /**
   * Sets housebroken.
   *
   * @param housebroken the housebroken
   */
  public void setHousebroken(boolean housebroken) {
    this.housebroken = housebroken;
  }

  /**
   * Is the dog a purebred.
   *
   * @return the boolean
   */
  public boolean isPurebred() {
    return purebred;
  }

  /**
   * Sets purebred status.
   *
   * @param purebred the purebred
   */
  public void setPurebred(boolean purebred) {
    this.purebred = purebred;
  }
}
