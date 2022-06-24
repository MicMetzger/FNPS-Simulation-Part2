package main.java.com.individuals;


import static main.java.com.Builders.NAME_TEMPLATE;

import java.security.*;
import java.util.*;
import main.java.com.individuals.training.*;
import main.java.com.item.*;



public class Trainer extends Employee {
  private String          name = "";
  public  TrainerStrategy trainingType;


  public Trainer(String trainingAlgo) {
    super();
    int num = new Random().nextInt(NAME_TEMPLATE.size());
    this.name = NAME_TEMPLATE.get(num);
    NAME_TEMPLATE.remove(num);
    super.base = this;
    super.ACTIVE = false;
    super.state = EmployeeState.IDLE;
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

  public void startTraining() {
    boolean animalExists = false;
    for (Item animal : inventory) {
      if (animal.isPet()) {
        animalExists = true;
        break;
      }
    }
    String announcement =
        animalExists ? " trains the animals" : " checks the animals for training, but notices that the case is empty. Skipping training.";
    announce(announcement);
    for (Item animal : inventory) {
      if (animal.isPet()) {
        ((Pet) animal).setHealthy(this.train(((Pet) animal).isHealthy(), ((Pet) animal)));
      }
    }
  }

  public boolean train(boolean houseBroken, Pet animal) {
    return trainingType.training(houseBroken, animal);
  }
}
