package main.java.com.item.addOns;

import main.java.com.item.*;


public class Microchip extends ItemDecorator{
    static final double price = 50.00;

    public Microchip(Item newItem) {
        super(newItem);
        System.out.println("  -- A microchip has been added for $50.");
    }



    public double getSalePrice() {
        return Math.round(concreteItem.getSalePrice() + price);
    }


}
