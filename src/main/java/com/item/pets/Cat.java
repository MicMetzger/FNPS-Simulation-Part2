package main.java.com.item.pets;

import main.java.com.item.Pet;
import main.java.com.item.pets.enums.Animal;
import main.java.com.item.pets.enums.AnimalType;
import main.java.com.item.pets.enums.Breed;

import java.security.SecureRandom;

import static java.lang.Math.round;
import static main.java.com.item.pets.enums.Animal.CATS;

/**
 * The type Cat.
 */
public class Cat extends Pet {

  // The Color
  public String  color;
  // The Housebroken
  public boolean housebroken;
  // The Purebred
  public boolean purebred;

  /**
   * Constructor Instantiates a new Cat, aswell as it's parent classes.
   *
   * @param name          the name
   * @param dayArrived    the day arrived
   * @param daySold       the day sold
   * @param purchasePrice the purchase price
   * @param listPrice     the list price
   * @param salePrice     the sale price
   * @param animal        the animal and it's breed
   * @param age           the age
   * @param healthy       the healthy
   * @param color         the color
   * @param housebroken   the housebroken
   * @param purebred      the purebred
   */
  public Cat(
      String name,
      int dayArrived,
      int daySold,
      double purchasePrice,
      double listPrice,
      double salePrice,
      Animal animal,
      int age,
      boolean healthy,
      String color,
      boolean housebroken,
      boolean purebred) {
    super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, animal, age, healthy);
    this.color       = color;
    this.housebroken = housebroken;
    this.purebred    = purebred;
    super.animal     = CATS.get(new SecureRandom().nextInt(4));
  }

  /**
   * Constructor Instantiates a new Cat as well as it's parent class.
   *
   * @param animal        the animal and it's breed
   * @param age         the age
   * @param healthy     the healthy
   * @param color       the color
   * @param housebroken the housebroken
   * @param purebred    the purebred
   */
  public Cat(Animal animal, int age, boolean healthy, String color, boolean housebroken, boolean purebred) {
    super(animal, age, healthy);
    this.color       = color;
    this.housebroken = housebroken;
    this.purebred    = purebred;
    super.animal     = CATS.get(new SecureRandom().nextInt(4));
  }

  /**
   * Constructor Instantiates a new Cat, it's parent values are left null.
   *
   * @param color       the color
   * @param housebroken the housebroken
   * @param purebred    the purebred
   */
  public Cat(String color, boolean housebroken, boolean purebred) {
    super();
    super.setDayArrived(0);
    super.setName("Cat");
    this.color       = color;
    this.housebroken = housebroken;
    this.purebred    = purebred;
    super.animal     = CATS.get(new SecureRandom().nextInt(4));
  }

  /**
   * Constructor Instantiates a new Cat of state values null, and instantiates its parent class.
   *
   * @param animal        the animal and it's breed
   * @param age     the age
   * @param healthy the healthy
   */
  public Cat(Animal animal, int age, boolean healthy) {
    super(animal, age, healthy);
    super.animal = CATS.get(new SecureRandom().nextInt(4));
  }

  /**
   * Default constructor of a Cat object.
   */
  public Cat() {
    super();
    super.setAnimal(AnimalType.Cat);
    super.setBreed(CATS.get(new SecureRandom().nextInt(4)).getType());
  }

  @Override
  public String toString() {
    return "Cat{"
        + "color='"
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
   * Gets the cat's color type.
   *
   * @return the color
   */
  public String getColor() {
    return color;
  }

  /**
   * Sets color type.
   *
   * @param color the cat's color
   */
  public void setColor(String color) {
    this.color = color;
  }

  /**
   * Is the cat housebroken.
   *
   * @return the boolean
   */
  public boolean isHousebroken() {
    return housebroken;
  }

  /**
   * Sets housebroken status.
   *
   * @param housebroken the housebroken
   */
  public void setHousebroken(boolean housebroken) {
    this.housebroken = housebroken;
  }

  /**
   * Is the cat a purebred.
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