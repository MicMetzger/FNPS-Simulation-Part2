package main.java.com.individuals.task;

public enum EventState {
  OPEN("Open"),
  ACTIVE("Active"),
  CHANGED("Changed"),
  CLOSED("Closed");
//  IDLE("Idle");

  public final String name;

  EventState(String name) {
    this.name = name;
  }
}


