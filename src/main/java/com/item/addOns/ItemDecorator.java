package main.java.com.item.addOns;

import main.java.com.item.*;

abstract public class ItemDecorator extends Item {
    protected Item concreteItem;

    public ItemDecorator(Item newItem) {
        concreteItem = newItem;
    }

    public double getSalePrice() {
        System.out.println("Base price: " + concreteItem.getSalePrice());
        return concreteItem.getSalePrice();
    }
}
