package main.java.com.individuals.training;
import main.java.com.item.Pet;

import java.security.SecureRandom;


/**
 * 10% chance of toggling the house broken attribute(if True, becomes False; if False becomes True)
 */
public class Haphazard implements TrainerStrategy {

    @Override
    public boolean training(boolean houseBroken, Pet animal) {
        SecureRandom rand = new SecureRandom();
        boolean trained = rand.nextInt(100) < 10;
        //if trained, then result will be !houseBroken
        boolean result = trained != houseBroken;
        System.out.println("The trainer performs Haphazard.");
        if(trained) {
            System.out.println("[+] During the training, " + (result ? animal.getName() + " became housebroken" : "the animal failed to become housebroken"));
        } else {
            System.out.println("The training failed.");
        }
        return result;
    }
}
