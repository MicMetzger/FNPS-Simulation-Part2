package main.java.com.item.supplies;
import main.java.com.item.pet.Animal;

import java.security.SecureRandom;

import static java.lang.Math.round;


public class Food extends Supplies {
	private int  size;
	private Animal  animal;
	private Type type;


	public Food(String name, double purchasePrice, double listPrice, double salePrice, int dayArrived, int daySold, int size, Animal animal, Type type) {
		super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
		this.size = size;
		this.animal = animal;
		this.type = type;
	}

	public Food() {
		super.supplyType = SupplyType.Food;
	}


	public Food(int size, Animal animal, Type type) {
		super(animal.toString() + " Food");
		double newPurchasePrice = round(new SecureRandom().nextDouble(100));
		super.setPurchasePrice(newPurchasePrice);
		super.setListPrice(round(newPurchasePrice*(double)2));
		super.setDayArrived(0);
		this.size = size;
		this.animal = animal;
		this.type = type;
		super.supplyType = SupplyType.Food;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getAnimalS() {
		return animal.toString();
	}

	
	public Animal getAnimal() {
		return animal;
	}


	public void setAnimal(Animal animal) {
		this.animal = animal;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}

}
