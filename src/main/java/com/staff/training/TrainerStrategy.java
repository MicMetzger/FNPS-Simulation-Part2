package main.java.com.staff.training;

import main.java.com.item.Pet;

/**
 * 50% chance of changing from False to True
 * Strategy Pattern
 */
public interface TrainerStrategy {

    public boolean training(boolean houseBroken, Pet animal);
}
