package main.java.com.item.supplies;

import main.java.com.item.pets.enums.*;

public class Treat extends Supplies {
  private final AnimalType animal;

  public Treat(AnimalType animal) {
    super();
    super.setName("Treat");
    this.animal = animal;
  }

  public Treat(
          String name,
          double purchasePrice,
          double listPrice,
          double salePrice,
          int daySold,
          int dayArrived,
          AnimalType animal) {
    super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
    super.setName("Treat");
    this.animal = animal;
  }

  public AnimalType getAnimal() {
    return animal;
  }
}
