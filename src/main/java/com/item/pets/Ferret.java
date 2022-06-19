package main.java.com.item.pets;

import main.java.com.item.Pet;
import main.java.com.item.pets.enums.Animal;
import main.java.com.item.pets.enums.Breed;




public class Ferret extends Pet {
  private String color;
  private boolean housebroken;

  public Ferret(String color, boolean housebroken, Animal animal, int age, boolean healthy) {
    super(animal, age, healthy);
    this.color = color;
    this.housebroken = housebroken;
  }

  public Ferret(String color, boolean housebroken) {}

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public boolean isHousebroken() {
    return housebroken;
  }

  public void setHousebroken(boolean housebroken) {
    this.housebroken = housebroken;
  }
}
