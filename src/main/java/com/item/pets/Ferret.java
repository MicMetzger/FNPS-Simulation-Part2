package main.java.com.item.pets;

import main.java.com.item.Pet;
import main.java.com.item.pets.enums.Animal;
import main.java.com.item.pets.enums.Color;


public class Ferret extends Pet {
  private Color color;
  private boolean housebroken;

  public Ferret(Animal animal, int age, boolean healthy, Color color, boolean housebroken , String name, int dayArrived, int daySold, double purchasePrice, double listPrice, double salePrice) {
    super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, animal, age, healthy);
    super.setName("Ferret");
    this.color = color;
    this.housebroken = housebroken;
  }

  public Ferret(Color color, boolean housebroken) {
    super.setName("Ferret");
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public boolean isHousebroken() {
    return housebroken;
  }

  public void setHousebroken(boolean housebroken) {
    this.housebroken = housebroken;
  }
}
