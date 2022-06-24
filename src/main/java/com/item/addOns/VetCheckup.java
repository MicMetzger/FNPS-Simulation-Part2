package main.java.com.item.addOns;

import java.security.*;
import main.java.com.item.*;

public class VetCheckup extends ItemDecorator {
    // 1 - 4 checkups
    static final int quantity = new SecureRandom().nextInt(1, 5);
    static final double price = quantity * 25.00;

    public VetCheckup(Item newItem) {
        super(newItem);
        System.out.println("  -- " + quantity + " Vet Checkups has been added for $" + quantity * 25);
    }

    public double getSalePrice() {
        return Math.round(concreteItem.getSalePrice() + price);
    }


}
