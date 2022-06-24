package main.java.com.item.addOns;

import main.java.com.item.*;


public class Insurance extends ItemDecorator{
    double price = 50.00;

    public Insurance(Item newItem) {
        super(newItem);
        System.out.println("  -- Insurance has been added for $50.");
    }


    public double getSalePrice() {
        return Math.round(concreteItem.getSalePrice() + price);
    }


}
