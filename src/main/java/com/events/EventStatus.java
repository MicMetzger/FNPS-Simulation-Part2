package main.java.com.events;

public enum EventStatus {
  INCOMPLETE("Incomplete"),
  COMPLETE("Complete"),
  RUNNING("Running"),
  PAUSED("PAUSED");
  

  private boolean assigned;
  private final String name;

  EventStatus(String name) {
    this.name = name;
    this.assigned = false;
  }

  private String getName() {
    return name;
  }
  public boolean isAssigned() {
    return assigned;
  }
  
  public void setAssigned(boolean assigned) {
    this.assigned = assigned;
  }
}
