package main.java.com.item;


import java.security.SecureRandom;

import static java.lang.Math.round;

/**
 *
 */
public abstract class Item {
	private String name;
	private int   dayArrived;
	private int   daySold;
	private double purchasePrice;
	private double listPrice;
	private double salePrice;


	/**
	 * Instantiates a new Item.
	 *
	 * @param name          the name
	 * @param dayArrived    the day arrived
	 * @param daySold       the day sold
	 * @param purchasePrice the purchase price
	 * @param listPrice     the list price
	 * @param salePrice     the sale price
	 */
	public Item(String name, double purchasePrice, double listPrice, double salePrice, int dayArrived, int daySold) {
		this.name = name;
		this.dayArrived = dayArrived;
		this.daySold = daySold;
		this.purchasePrice = purchasePrice;
		this.listPrice = listPrice;
		this.salePrice = salePrice;
	}


	/**
	 * Default constructor of Item object.
	 */
	public Item() {
		double newPurchasePrice = round(new SecureRandom().nextDouble(100));
		setPurchasePrice(newPurchasePrice);
		setListPrice(round(newPurchasePrice*(double)2));
	}


	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		if(name == null) return "[-] ERROR: NAME NOT SET.";
		return name;
	}


	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets purchase price.
	 *
	 * @return the purchase price
	 */
	public double getPurchasePrice() {
		return purchasePrice;
	}


	/**
	 * Sets purchase price.
	 *
	 * @param purchasePrice the purchase price
	 */
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	/**
	 * Gets list price.
	 *
	 * @return the list price
	 */
	public double getListPrice() {
		return listPrice;
	}


	/**
	 * Sets list price.
	 *
	 * @param listPrice the list price
	 */
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}


	/**
	 * Gets sale price.
	 *
	 * @return the sale price
	 */
	public double getSalePrice() {
		return salePrice;
	}


	/**
	 * Sets sale price.
	 *
	 * @param salePrice the sale price
	 */
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}


	/**
	 * Gets day arrived.
	 *
	 * @return the day arrived
	 */
	public int getDayArrived() {
		return dayArrived;
	}


	/**
	 * Sets day arrived.
	 *
	 * @param dayArrived the day arrived
	 */
	public void setDayArrived(int dayArrived) {
		this.dayArrived = dayArrived;
	}


	/**
	 * Gets day sold.
	 *
	 * @return the day sold
	 */
	public int getDaySold() {
		return daySold;
	}


	/**
	 * Sets day sold.
	 *
	 * @param daySold the day sold
	 */
	public void setDaySold(int daySold) {
		this.daySold = daySold;
	}


	public void print() {
		System.out.println("Item{" +
				"name='" + name + '\'' +
				", dayArrived=" + dayArrived +
				", daySold=" + daySold +
				", purchasePrice=" + purchasePrice +
				", listPrice=" + listPrice +
				", salePrice=" + salePrice +
				'}');
	}


	public abstract void announce();

}
