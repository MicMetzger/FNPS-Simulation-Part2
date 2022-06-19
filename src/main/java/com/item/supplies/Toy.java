package main.java.com.item.supplies;
import main.java.com.item.pet.Animal;
import main.java.com.item.pet.Pet;

import java.security.SecureRandom;

import static java.lang.Math.round;


public class Toy extends Supplies {

	// The animal
	private Animal animal;


	/**
	 * Instantiates a new Leash
	 *
	 * @param animal the animal
	 */

	public Toy(String name, double purchasePrice, double listPrice, double salePrice, int daySold, int dayArrived, Animal animal) {
		super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
		this.animal = animal;
	}

	public Toy(Animal animal) {
		double newPurchasePrice = round(new SecureRandom().nextDouble(100));
		super.setPurchasePrice(newPurchasePrice);
		super.setListPrice(round(newPurchasePrice*(double)2));
		super.setDayArrived(0);
		super.setName("Toy");
		this.animal = animal;
	}


	/**
	 * Default constructor
	 */

	public Toy() {
		super();
		super.supplyType = SupplyType.Toy;
	}

}
