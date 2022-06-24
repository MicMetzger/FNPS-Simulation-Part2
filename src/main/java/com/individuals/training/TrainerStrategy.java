package main.java.com.individuals.training;

import main.java.com.item.*;

/**
 * 50% chance of changing from False to True
 * Strategy Pattern
 */
public interface TrainerStrategy {

    boolean training(boolean houseBroken, Pet animal);
}
