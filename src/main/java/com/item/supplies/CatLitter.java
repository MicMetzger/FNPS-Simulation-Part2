package main.java.com.item.supplies;


import java.security.SecureRandom;

import static java.lang.Math.round;

/**
 * The type Cat liter.
 */
public class CatLitter extends Supplies {
    // The size of the cat liter.
    private int    size;



    /**
     * Instantiates a new Cat liter.
     *
     * @param size the size
     */
    public CatLitter(int size) {
        super.setDayArrived(0);
        super.setName("Cat Litter");
        this.size = size;
    }


    /**
     * Default Constructor of CatLiter object
     */
    public CatLitter() {}

    public CatLitter(String name, double purchasePrice, double listPrice, double salePrice, int daySold, int dayArrived, int size) {
        super(name, purchasePrice, listPrice, salePrice, dayArrived, daySold);
        super.setDayArrived(0);
        super.setName("CatLitter");
        double newPurchasePrice = round(new SecureRandom().nextDouble(100));
        super.setPurchasePrice(newPurchasePrice);
        super.setListPrice(round(newPurchasePrice*(double)2));
        this.size = size;
    }

    /**
     * Gets the cat liter size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }


    /**
     * Sets the cat liter size.
     *
     * @param size the cat liter size
     */
    public void setSize(int size) {
        this.size = size;
    }

}
