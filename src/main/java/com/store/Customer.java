package main.java.com.store;
import main.java.com.item.Item;
import main.java.com.item.supplies.PurchaseType;
import java.security.SecureRandom;
import java.util.ArrayList;
import static java.lang.Math.round;



public class Customer {
	PurchaseType desire;
	Item obj;

	boolean discount;

	double purchasePrice;
	
	Customer() {
		desire = PurchaseType.values()[new SecureRandom().nextInt(PurchaseType.values().length)];
		obj = null;
		purchasePrice = 0;
		discount = false;
	}
	
	public boolean inspectInventory(ArrayList<Item> inventory) {
		System.out.println("The customer inspects the store's offerings...");
		inventory.forEach(item -> {
			SecureRandom rand = new SecureRandom();
			int roll = rand.nextInt(0, 10);

			if (roll == 1) {
				// TODO: add a functionality where the desired item is out of stock
				if(rand.nextInt(100) < 50) { // 50% chance of buying the item at listPrice
					obj = item;
					purchasePrice = obj.getListPrice();
					obj.setSalePrice(purchasePrice);
				} else {
					if(rand.nextInt(100) < 75) {
						discount = true;
						obj = item;
						purchasePrice = round(obj.getListPrice() - (obj.getListPrice() /100) * 10); // 10% off discount
						obj.setSalePrice(purchasePrice);
					}
				}
			}
		});
		return obj != null;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public boolean hasBoughtAtDisountPrice () {
		return discount;
	}
}
