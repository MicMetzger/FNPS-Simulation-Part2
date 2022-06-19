package main.java.com.item.pet;

import java.security.SecureRandom;

import static java.lang.Math.round;

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
	 * Constructor
	 * Instantiates a new Cat, aswell as it's parent classes.
	 *
	 * @param name          the name
	 * @param dayArrived    the day arrived
	 * @param daySold       the day sold
	 * @param purchasePrice the purchase price
	 * @param listPrice     the list price
	 * @param salePrice     the sale price
	 * @param breed         the breed
	 * @param age           the age
	 * @param healthy       the healthy
	 * @param color         the color
	 * @param housebroken   the housebroken
	 * @param purebred      the purebred
	 */
	public Cat(String name, int dayArrived, int daySold, double purchasePrice, double listPrice, double salePrice, Breed breed, int age, boolean healthy, String color, boolean housebroken, boolean purebred) {
		super(name, dayArrived, daySold, purchasePrice, listPrice, salePrice, breed, age, healthy);
		this.color = color;
		this.housebroken = housebroken;
		this.purebred = purebred;
		super.animalType = Animal.Cat;
	}


	/**
	 * Constructor
	 * Instantiates a new Cat as well as it's parent class.
	 *
	 * @param breed       the breed
	 * @param age         the age
	 * @param healthy     the healthy
	 * @param color       the color
	 * @param housebroken the housebroken
	 * @param purebred    the purebred
	 */
	public Cat(Breed breed, int age, boolean healthy, String color, boolean housebroken, boolean purebred) {
		super(breed, age, healthy);
		this.color = color;
		this.housebroken = housebroken;
		this.purebred = purebred;
		super.animalType = Animal.Cat;
	}


	/**
	 * Constructor
	 * Instantiates a new Cat, it's parent values are left null.
	 *
	 * @param color       the color
	 * @param housebroken the housebroken
	 * @param purebred    the purebred
	 */
	public Cat(String color, boolean housebroken, boolean purebred) {
		super();
		super.setDayArrived(0);
		super.setName("Cat");
		this.color = color;
		this.housebroken = housebroken;
		this.purebred = purebred;
		super.animalType = Animal.Cat;

	}


	/**
	 * Constructor
	 * Instantiates a new Cat of state values null, and instantiates its parent class.
	 *
	 * @param breed   the breed
	 * @param age     the age
	 * @param healthy the healthy
	 */
	public Cat(Breed breed, int age, boolean healthy) {
		super(breed, age, healthy);
		super.animalType = Animal.Cat;

	}


	/**
	 * Default constructor of a Cat object.
	 */
	public Cat() {
		super();
		super.animalType = Animal.Cat;
	}


	@Override
	public String toString() {
		return "Cat{" +
				"color='" + color + '\'' +
				", housebroken=" + housebroken +
				", purebred=" + purebred +
				"} " + super.toString();
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
