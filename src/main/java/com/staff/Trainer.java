package main.java.com.staff;

import main.java.com.staff.training.Haphazard;
import main.java.com.staff.training.NegativeReinforcement;
import main.java.com.staff.training.PositiveReinforcement;
import main.java.com.staff.training.TrainerStrategy;

import java.security.SecureRandom;
import java.util.Random;

public class Trainer extends Employee {
  private String name = "";
  public TrainerStrategy trainingType;

  public Trainer() {
    super();
    int num = new Random().nextInt(NAME_TEMPLATE.size());
    this.name = NAME_TEMPLATE.get(num);
    NAME_TEMPLATE.remove(num);
    super.base = this;
    setTrainingTypeRandomly();
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

  public void setTrainingTypeRandomly() {
    // range of  1 - 3
    int roll = new SecureRandom().nextInt(4);
    switch (roll) {
      case 1 -> {
        this.trainingType = new PositiveReinforcement();
        System.out.println("Positive Reinforcement has been assigned to " + this.name);
      }
      case 2 -> {
        this.trainingType = new NegativeReinforcement();
        System.out.println("Negative Reinforcement has been assigned to " + this.name);
      }
      case 3 -> {
        this.trainingType = new Haphazard();
        System.out.println("Haphazard has been assigned to " + this.name);
      }
    }
  }
}
