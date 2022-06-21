package main.java.com.individuals;

import static main.java.com.Builders.NAME_TEMPLATE;

import main.java.com.individuals.training.Haphazard;
import main.java.com.individuals.training.NegativeReinforcement;
import main.java.com.individuals.training.PositiveReinforcement;
import main.java.com.individuals.training.TrainerStrategy;
import java.security.SecureRandom;

import java.util.Random;



public class Trainer extends Employee {

  private String          name = "";
  public  TrainerStrategy trainingType;


  public Trainer(String trainingAlgo) {
    super();
    int num = new Random().nextInt(NAME_TEMPLATE.size());
    this.name = NAME_TEMPLATE.get(num);
    NAME_TEMPLATE.remove(num);
    super.base = this;
    setTrainingType(trainingAlgo);
  }

  @Override
  public String getName() {
    return name + ", the Trainer,";
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public boolean trainAnimal(boolean houseBroken) {
    return trainingType.training(houseBroken);
  }

  public void setTrainingType(String trainingAlgo) {
    // range of  1 - 3
    int roll = new SecureRandom().nextInt(3);
    switch (trainingAlgo) {
      case "Haphazard" -> {
        this.trainingType = new PositiveReinforcement();
        System.out.println("Positive Reinforcement has been assigned to " + this.name);
      }
      case "Negative" -> {
        this.trainingType = new NegativeReinforcement();
        System.out.println("Negative Reinforcement has been assigned to " + this.name);
      }
      case "Positive" -> {
        this.trainingType = new Haphazard();
        System.out.println("Haphazard has been assigned to " + this.name);
      }
    }
  }

  public boolean train(boolean houseBroken) {
    return trainingType.training(houseBroken);
  }
}