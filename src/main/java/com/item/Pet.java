package main.java.com.item;
import main.java.com.item.pets.enums.Animal;
import main.java.com.item.pets.enums.AnimalType;
import main.java.com.item.pets.enums.Breed;

public abstract class Pet extends Item {
  protected Animal animal;
  int age;
  boolean healthy;

  public Pet(
      String name,
      int dayArrived,
      int daySold,
      double purchasePrice,
      double listPrice,
      double salePrice,
      Animal animal,
      int age,
      boolean healthy) {
    super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold, true);
    this.animal = animal;
    this.age = age;
    this.healthy = healthy;
    super.setAnimalIdentifier(true);
  }

  public Pet(Animal animal, int age, boolean healthy) {
    super();
    super.setAnimalIdentifier(true);
    this.animal = animal;
    this.age = age;
    this.healthy = healthy;
  }

  public Pet(Animal animal) {
    super();
    super.setAnimalIdentifier(true);
    this.animal = animal;
  }

  public Pet() {
    super.setAnimalIdentifier(true);
  }

  @Override
  public void setName(String name) {
    super.setName(name);
  }

  public void announce() {}

  /**
   * Sets list price.
   *
   * @param listPrice the list price
   */
  @Override
  public void setListPrice(double listPrice) {
    super.setListPrice(listPrice);
  }

  /**
   * Sets sale price.
   *
   * @param salePrice the sale price
   */
  @Override
  public void setSalePrice(double salePrice) {
    super.setSalePrice(salePrice);
  }

  public Breed getBreed() {
    return this.animal.breed;
  }

  public void setBreed(Breed breed) {
    this.animal.breed = breed;
  }

  public void setAnimal(AnimalType animal) {
    this.animal.type = animal;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isHealthy() {
    return healthy;
  }

  public int setHealthy(boolean healthy) {
    this.healthy = healthy;
    return (this.healthy) ? 1 : 0;
  }

  @Override
  public String toString() {
    return "Pet{"
        + "breed="
        + animal.breed
        + ", age="
        + age
        + ", healthy="
        + healthy
        + "} "
        + super.toString();
  }
}
