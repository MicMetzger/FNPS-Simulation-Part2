package main.java.com.item.pet;
import main.java.com.item.Item;




public abstract class Pet extends Item {
	Breed   breed;
	Animal  animalType;
	int     age;
	boolean healthy;


	public Pet(String name, int dayArrived, int daySold, double purchasePrice, double listPrice, double salePrice, Breed breed, int age, boolean healthy) {
		super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
		this.breed   = breed;
		this.age     = age;
		this.healthy = healthy;
	}


	public Pet(Breed breed, int age, boolean healthy) {
		super();
		this.breed   = breed;
		this.age     = age;
		this.healthy = healthy;
	}


	public Pet(Breed breed) {
		super();
		this.breed = breed;
	}


	public Pet() {
		super();
	}


	@Override
	public void setName(String name) {
		super.setName(name);
	}


	public void announce() {

	}


	/**
	 * Sets list price.
	 *
	 * @param listPrice the list price
	 */
	@Override
	public void setListPrice(double listPrice) {
		super.setListPrice(listPrice);
	}


	/**
	 * Sets sale price.
	 *
	 * @param salePrice the sale price
	 */
	@Override
	public void setSalePrice(double salePrice) {
		super.setSalePrice(salePrice);
	}


	public Breed getBreed() {
		return breed;
	}


	public void setBreed(Breed breed) {
		this.breed = breed;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public boolean isHealthy() {
		return healthy;
	}


	public int setHealthy(boolean healthy) {
		this.healthy = healthy;
		return (this.healthy) ? 1: 0;
	}


	@Override
	public String toString() {
		return "Pet{" +
				"breed=" + breed +
				", age=" + age +
				", healthy=" + healthy +
				"} " + super.toString();
	}

}


