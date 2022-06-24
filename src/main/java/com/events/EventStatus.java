package main.java.com.events;

public enum EventStatus {
  INCOMPLETE("Incomplete"),
  COMPLETE("Complete"),
  RUNNING("Running"),
  PAUSED("PAUSED");

  private final String name;

  EventStatus(String name) {
    this.name = name;
  }

  private String getName() {
    return name;
  }
}
