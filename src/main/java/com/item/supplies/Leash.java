package main.java.com.item.supplies;
import main.java.com.item.pet.Animal;

import java.security.SecureRandom;

import static java.lang.Math.round;


public class Leash extends Supplies {

	// The animal
	private Animal animal;


	/**
	 * Instantiates a new Leash
	 *
	 * @param animal the animal
	 */

	public Leash(Animal animal) {
		super();
		super.setDayArrived(0);
		super.setName("Leash");
		this.animal = animal;
	}


	public Leash(String animal) {
		this.animal = Animal.valueOf(animal);
	}


	/**
	 * Default constructor
	 */

	public Leash(String name, double purchasePrice, double listPrice, double salePrice, int dayArrived, int daySold, Animal animal) {
		super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
		this.animal = animal;
	}

	/**
	 * Gets the animal
	 *
	 * @return the animal
	 */
	public Animal getAnimal() {
		return animal;
	}


	/**
	 * Sets the animal
	 *
	 * @param animal the animal
	 */
	public void setAnimal(String animal) {
		this.animal = Animal.valueOf(animal);
	}

}
