package main.java.com.item.pets;

import static main.java.com.item.pets.enums.Animal.*;

import java.security.*;
import main.java.com.item.*;
import main.java.com.item.pets.enums.*;



public class Snake extends Pet {
  private double size;

  public Snake(Animal animal, int age, boolean healthy, double size, String name, int dayArrived, int daySold, double purchasePrice, double listPrice,
      double salePrice) {
    super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, animal, age, healthy);
    super.setName("Snake");
    this.size = size;
  }


  public Snake(double size) {
    super();
    super.setName("Snake");
    this.size    = size;
    super.animal = SNAKES.get(new SecureRandom().nextInt(4));
  }
  
  public Snake() {
    super();
    super.setName("Snake");
    super.animal = SNAKES.get(new SecureRandom().nextInt(4));
  }


  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }
}
