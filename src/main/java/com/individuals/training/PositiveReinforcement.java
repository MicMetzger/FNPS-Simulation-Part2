package main.java.com.individuals.training;
import java.security.SecureRandom;

/**
 * 50% chance of changing from False to True
 */
public class PositiveReinforcement implements TrainerStrategy {

    @Override
    public boolean training(boolean houseBroken) {
        SecureRandom rand = new SecureRandom();
        boolean trained = rand.nextInt(100) < 50;
        boolean result = houseBroken || trained;
        System.out.println("The trainer performs Positive Reinforcement.");

        if(!trained) {
            System.out.println("The training failed.");
        } else if(!houseBroken) {
            System.out.println("During the training, the animal became housebroken.");
        }
        return result;
    }
}