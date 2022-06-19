package main.java.com.staff;
import main.java.com.item.Item;
import main.java.com.item.pet.*;
import main.java.com.item.supplies.*;
import main.java.com.store.DeliveryPackage;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;




public class Employee {
	private int workedDays;
	Employee        base;
	ArrayList<Item> inventory;
	ArrayList<Pet>  sick;

	double cash;

	ArrayList<DeliveryPackage> mailBox;

	static ArrayList<String> NAME_TEMPLATE = new ArrayList<String>(Arrays.asList("Kevin", "Andrew", "Michelle", "David", "Sarah"));


	public Employee(int workedDays) {
		this.workedDays = workedDays;
		inventory       = new ArrayList<>();
		cash            = 0;
		mailBox = new ArrayList<>();
	}


	public Employee() {
		workedDays    = 0;
		inventory     = new ArrayList<>();
		cash          = 0;
		mailBox = new ArrayList<>();
	}


	public String getName() {
		return base.getName();
	}


	public void setName(String name) {
		base.setName(name);
	}


	public void announce(String announcement) {
		System.out.println(getName() + announcement);
	}


	public void arrival() {
		String announcement = " enters the store...";
		announce(announcement);
	}


	private void leave() {
		String announcement = " leaves the store...";
		announce(announcement);
	}


	public void feedAnimals() {
		ArrayList<Item> itemsToBeRemoved = new ArrayList<>();
		inventory.forEach(item -> {
			if (item.getClass().getCanonicalName().contains("pet")) {
				// 5% chance of getting sick
				/* = ((Pet) item).setHealthy(rand.nextInt(0,100) < 5);*/
				int getsSick = new SecureRandom().nextInt(2);
				switch (getsSick) {
					case 0 -> {
						((Pet) item).setHealthy(false);
						announce(" visits a " /*+ ((Pet) item).getBreed().name + " "*/ + ((Pet) item).getClass().getSimpleName() + ", and the pet got sick...");
						sick.add(((Pet) item));
						itemsToBeRemoved.add(item); // preventing error
						break;
					}
					case 1 -> {
						announce(" visits a " /*+ ((Pet) item).getBreed().name + " "*/ + ((Pet) item).getClass().getSimpleName());
						announce(" feeds the " /*+ ((Pet) item).getBreed().name + " "*/ + ((Pet) item).getClass().getSimpleName());
						break;
					}
				}
			}
		});
		inventory.removeAll(itemsToBeRemoved);
		itemsToBeRemoved.clear();

		for (Pet pet : sick) {
			// 25% change of recovering
			switch (pet.setHealthy(new SecureRandom().nextInt(100) < 25)) {
				case 0 -> {
					announce(" feeds a sick " /*+ ((Pet) item).getBreed().name + " "*/ + pet.getClass().getSimpleName() + " and the pet remains ill...");
					break;
				}
				case 1 -> {
					announce(" feeds a sick " /*+ ((Pet) item).getBreed().name + " "*/ + pet.getClass().getSimpleName() + " and the pet recovered from its sickness...");
					inventory.add(pet);
					itemsToBeRemoved.add(pet); // preventing error
					break;
				}
			}
		}
		sick.removeAll(itemsToBeRemoved);
	}



	public void processInventory() {
		String announcement = " goes through store inventory...";
		announce(announcement);
	}


	public void CheckRegister() {
		String announcement = " checks the register...";

	}


	public void goToBank() {
		String announcement = " goes to the bank...";
		announce(announcement);

		cash += 1000;
	}


	public void processDeliveries() {
		String announcement = " goes through today's deliveries...";
		announce(announcement);
		ArrayList<DeliveryPackage> RECEIVED_PACKAGES = new ArrayList<DeliveryPackage>();

		if(mailBox.size() != 0) {
			mailBox.forEach(item -> {
				if (item.getExpectedDeliveryDate() == workedDays) {
					String announcementDelivery = " " + item.getPackageName() + " has arrived and is added to the inventory.";
					announce(announcementDelivery);
					inventory.add(item.getItem());
					RECEIVED_PACKAGES.add(item);
				}
			});
			mailBox.removeAll(RECEIVED_PACKAGES);
		}
		else { // mailbox empty
			String announcementError = " notices that the mailbox is empty!";
			announce(announcementError);
		}

	}


	/**
	 * @param name
	 * @param expectedDeliveryDate
	 * @param purchasePrice
	 * @return Delivery Package
	 */
	public DeliveryPackage orderItem(String name, int expectedDeliveryDate, double purchasePrice) {
		DecimalFormat sizeFormat 					= new DecimalFormat("#####.00");
		final ArrayList<String> colors              = new ArrayList<String>(Arrays.asList("Black", "Brown", "White", "Gray", "Red"));
		DeliveryPackage newPackage = new DeliveryPackage(name, expectedDeliveryDate);
		int daySold 	= 0;
		int salePrice 	= 0;
		int age			= new Random().nextInt(15);

		String announcement = " purchasing " + name + " for $" + purchasePrice;
		announce(announcement);

		// TODO: Make Breeds for each animals in Breed enum
		if(name == "Dog") {
			newPackage.setItem(new Dog(name, expectedDeliveryDate, daySold, purchasePrice, purchasePrice*2, salePrice, Breed.values()[new Random().nextInt(Breed.values().length)], age, new Random().nextInt(2) == 1, Double.parseDouble(sizeFormat.format(new Random().nextDouble(50))), Color.values()[new Random().nextInt(Color.values().length)], new Random().nextInt(2) == 1, new Random().nextInt(2) == 1));
		} else if(name == "Cat") {
			newPackage.setItem(new Cat(name, expectedDeliveryDate, daySold, purchasePrice, purchasePrice*2, salePrice, Breed.values()[new Random().nextInt(Breed.values().length)], age, new Random().nextInt(2) == 1, colors.get(new Random().nextInt(colors.size())), new Random().nextInt(2) == 1, new Random().nextInt(2) == 1));
		} else if (name == "Bird") {
			newPackage.setItem(new Bird(name, expectedDeliveryDate, daySold, purchasePrice, purchasePrice*2, salePrice, Breed.values()[new Random().nextInt(Breed.values().length)], age, new Random().nextInt(2) == 1, Double.parseDouble(sizeFormat.format(new Random().nextDouble(8))), new Random().nextInt(2) == 1, new Random().nextInt(2) == 1, new Random().nextInt(2) == 1));
		} else if(name == "Food") {
			newPackage.setItem(new Food(name, purchasePrice, purchasePrice*2, salePrice, daySold, expectedDeliveryDate, new Random().nextInt(100), Animal.values()[new Random().nextInt(Animal.values().length)], Type.values()[new Random().nextInt(Type.values().length)]));
		} else if(name == "Leash") {
			newPackage.setItem(new Leash(name, purchasePrice, purchasePrice*2, salePrice, daySold, expectedDeliveryDate, Animal.values()[new Random().nextInt(Animal.values().length)]));
		} else if (name == "Toy") {
			newPackage.setItem(new Toy(name, purchasePrice, purchasePrice*2, salePrice, daySold, expectedDeliveryDate, Animal.values()[new Random().nextInt(Animal.values().length)]));
		} else if(name == "CatLitter") {
			newPackage.setItem(new CatLitter(name, purchasePrice, purchasePrice*2, salePrice, daySold, expectedDeliveryDate, new Random().nextInt(100)));
		}

		return newPackage;
	}


	public double checkCashOnHand() {
		return this.cash;
	}


	public void doInventory() {
		// String announcement = "places an order for ";  //TODO\

		Random            rand            = new Random();
		ArrayList<String> itemToBeRemoved = new ArrayList<String>();
		ArrayList<String> ITEM_TO_ORDER   = new ArrayList<String>(Arrays.asList("Dog", "Cat", "Bird", "Food", "Leash", "Toy", "CatLitter"));
		String            announcement    = " checking the inventory...";
		double 			  totalInventoryValue = 0.0;

		announce(announcement);
		for(Item item:inventory) {
			// Initially (Day one), the value should be all zero as all purchase prices should be zero.
			totalInventoryValue += item.getPurchasePrice();
			String itemName = item.getClass().getSimpleName();
			if (ITEM_TO_ORDER.contains(itemName)) {
				itemToBeRemoved.add(itemName);
			}
		};

		announce(" reporting the total inventory value. Total Value: $" + totalInventoryValue);
		ITEM_TO_ORDER.removeAll(itemToBeRemoved);
		itemToBeRemoved.clear();

		// ITEM_TO_ORDER is now left with items that need to be ordered (0 stock)
		for (String name : ITEM_TO_ORDER) {
			System.out.println(name + " needs to be purchased.");
			int    expectedDeliveryDate = workedDays + rand.nextInt(3);
			double purchasePrice        = rand.nextInt(100);
			if (cash >= purchasePrice) {
				mailBox.add(orderItem(name, expectedDeliveryDate, purchasePrice));
				cash -= purchasePrice;
			}
			else {
				// insufficient money
				System.out.println("Purchase failed: Insufficient money");
				itemToBeRemoved.add(name);
			}
		}
		ITEM_TO_ORDER.clear();
	}


	public void cleanStore() {
		String announcement = " cleans the store...";
		announce(announcement);
		ArrayList<Pet> ESCAPING_ANIMALS = new ArrayList<Pet>();

		announce(" cleans the animal cage.");
		for(Item item:inventory) {
			if (item.getClass().getCanonicalName().contains("pet")) {
				if(new SecureRandom().nextInt(100) < 50) {
					announcement = " accidentally has escape " + item.getName();
					announce(announcement);
					if(new SecureRandom().nextInt(100) < 50) { // clerk catches escaping animal
						announce(" catches " + item.getName() + " and puts it back in the cage");
					} else { // animal escaped
						System.out.println("[-] " + item.getName() + " successfully escapes for freedom..........");
						ESCAPING_ANIMALS.add(((Pet) item));
					}
				}
			}
		};
		inventory.removeAll(ESCAPING_ANIMALS);
		ESCAPING_ANIMALS.clear();

		announce(" cleans the sick animal cage.");
		for(Pet item:sick) {
			if(new SecureRandom().nextInt(100) < 5) {
				announcement = " accidentally has escape " + item.getName();
				announce(announcement);
				if(new SecureRandom().nextInt(100) < 50) { // clerk catches escaping animal
					announce(" catches " + item.getName() + " and puts it back in the cage");
				} else { // animal escaped
					System.out.println("[-] " + item.getName() + " desperately escapes for freedom..........");
					ESCAPING_ANIMALS.add(((Pet) item));
				}
			}
		};
		sick.removeAll(ESCAPING_ANIMALS);
	}


	public int getWorkDays() {
		return workedDays;
	}

	public void incWorkDays() {
		workedDays++;
	}


	public void dayoff() {
		workedDays = 0;
	}


	public void setInventory(ArrayList<Item> newInventory) {
		this.inventory = newInventory;
	}


	public void setSickPet(ArrayList<Pet> newSickAnimals) {
		this.sick = newSickAnimals;
	}


	public ArrayList<DeliveryPackage> getMailBox() {
		return this.mailBox;
	}


	public void setMailBox(ArrayList<DeliveryPackage> newMailbox) {
		this.mailBox = newMailbox;
	}


	public void setCash(double newCash) {
		this.cash = newCash;
	}


	public double getCash() {
		return cash;
	}


	public ArrayList<Pet> getSickAnimal() {
		return sick;
	}


	public ArrayList<Item> getInventory() {
		return inventory;
	}




}
