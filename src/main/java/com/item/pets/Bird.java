package main.java.com.item.pets;

import static main.java.com.item.pets.enums.Animal.*;

import java.security.*;
import main.java.com.item.*;
import main.java.com.item.pets.enums.*;

public class Bird extends Pet {

  /**
   * the size
   */
  public double size;

  /**
   * the mimicry
   */
  public boolean mimicry;

  /**
   * the exotic
   */
  public boolean exotic;

  /**
   * the papers
   */
  public boolean papers;

  /**
   * Instantiates a new bird
   *
   * @param name
   * @param dayArrived
   * @param daySold
   * @param purchasePrice
   * @param listPrice
   * @param salePrice
   * @param animal        the animal and it's breed
   * @param age
   * @param healthy
   */
  public Bird(
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
      boolean mimicry,
      boolean exotic,
      boolean papers) {
    super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, animal, age, healthy);
    this.size    = size;
    this.mimicry = mimicry;
    this.exotic  = exotic;
    this.papers  = papers;
    super.animal = BIRDS.get(new SecureRandom().nextInt(4));
  }

  /**
   * Instantiates a new bird
   *
   * @param animal  the animal and it's breed
   * @param age
   * @param healthy
   * @param size
   * @param mimicry
   * @param exotic
   * @param papers
   */
  public Bird(
      Animal animal,
      int age,
      boolean healthy,
      double size,
      boolean mimicry,
      boolean exotic,
      boolean papers) {
    super(animal, age, healthy);
    super.setName("Bird");
    this.size    = size;
    this.mimicry = mimicry;
    this.exotic  = exotic;
    this.papers  = papers;
    super.animal = BIRDS.get(new SecureRandom().nextInt(4));
  }

  /**
   * Instantiates a new bird
   *
   * @param size
   * @param mimicry
   * @param exotic
   * @param papers
   */
  public Bird(double size, boolean mimicry, boolean exotic, boolean papers) {
    super();
    super.setDayArrived(0);
    super.setName("Bird");
    this.size    = size;
    this.mimicry = mimicry;
    this.exotic  = exotic;
    this.papers  = papers;
    super.animal = BIRDS.get(new SecureRandom().nextInt(4));
  }
  
  public Bird() {
    super();
    super.setDayArrived(0);
    super.setName("Bird");
    this.size    = 0;
    this.mimicry = false;
    this.exotic  = false;
    this.papers  = false;
    super.animal = BIRDS.get(new SecureRandom().nextInt(4));
  }


  @Override
  public String toString() {
    return "Bird{"
        + "size="
        + size
        + ", mimicry='"
        + mimicry
        + '\''
        + ", exotic="
        + exotic
        + ", papers="
        + papers
        + "} "
        + super.toString();
  }

  /**
   * sets the size
   *
   * @param size
   */
  public void setSize(double size) {
    this.size = size;
  }

  /**
   * sets the mimicry
   *
   * @param mimicry
   */
  public void setMimicry(boolean mimicry) {
    this.mimicry = mimicry;
  }

  /**
   * sets the exotic
   *
   * @param exotic
   */
  public void setExotic(boolean exotic) {
    this.exotic = exotic;
  }

  /**
   * sets the papers
   *
   * @param papers
   */
  public void setPapers(boolean papers) {
    this.papers = papers;
  }

  /**
   * @return the size
   */
  public double getSize() {
    return size;
  }

  /**
   * @return the mimicry
   */
  public boolean isMimicry() {
    return mimicry;
  }

  /**
   * @return the exotic
   */
  public boolean isExotic() {
    return exotic;
  }

  /**
   * @return the papers
   */
  public boolean isPapers() {
    return papers;
  }
}
