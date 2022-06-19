package main.java.com.item.supplies;

import main.java.com.item.pets.enums.AnimalType;

public class Treat extends Supplies {
  private final AnimalType animal;

  public Treat(AnimalType animal) {
    super();
    this.animal = animal;
  }

  public AnimalType getAnimal() {
    return animal;
  }
}
