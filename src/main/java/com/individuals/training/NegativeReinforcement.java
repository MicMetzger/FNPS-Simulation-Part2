package main.java.com.individuals.training;
import java.security.SecureRandom;

/**
 * 20% chance of house broken changing from True to False; 40% chance of changing from False to True
 */
public class NegativeReinforcement implements TrainerStrategy {

    @Override
    public boolean training(boolean houseBroken) {
        SecureRandom rand = new SecureRandom();
        boolean negative = rand.nextInt(100) < 20;
        boolean positive = rand.nextInt(100) < 40;
        boolean result = houseBroken ? !(negative) : positive;
        System.out.println("The trainer performs Negative Reinforcement.");

        if(houseBroken && negative) {
            System.out.println("During the training, the animal became unhousebroken.");
        } else if(!houseBroken && positive) {
            System.out.println("During the training, the animal became housebroken.");
        } else {
            System.out.println("The training failed.");
        }

        return result;
    }
}
