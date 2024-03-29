package main.java.com.events.task;

import main.java.com.events.*;
import main.java.com.individuals.*;
import main.java.com.store.*;



public abstract class EmployeeTask {
  private   TaskType    taskType;
  protected EventStatus eventStatus;
  private   Employee    employee;
  private   State       eventInstance;
  public    Store       instance;

  private State getEventInstance() {
    return eventInstance;
  }

  private void setEventInstance(State eventInstance) {
    this.eventInstance = eventInstance;
  }

  public EmployeeTask(TaskType taskType, State eventInstance) {
    this.taskType      = taskType;
    this.eventStatus   = EventStatus.INCOMPLETE;
    this.employee      = null;
    this.eventInstance = eventInstance;
  }

  public EmployeeTask(TaskType taskType, EventStatus eventStatus, Employee employee, State eventInstance) {
    this.taskType      = taskType;
    this.eventStatus   = eventStatus;
    this.employee      = employee;
    this.eventInstance = eventInstance;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Employee getEmployee() {
    return employee;
  }

  public EmployeeTask getEmployeeTask() {
    return this;
  }

  public enum TaskType {
    TASK_SELLING("Selling"),
    TASK_OPENING("Opening"),
    TASK_CLOSING("Closing"),
    TASK_PROCESSING("Processing"),
    TASK_INVENTORY("Inventory"),
    TASK_TRAINING("Training"),
    TASK_CLEANING("Cleaning"),
    TASK_BANKING("Banking"),
    TASK_FEEDING("Feeding");

    private final String name;

    public String taskname() {
      return name;
    }

    TaskType(String name) {
      this.name = name;
    }

  }


  public TaskType getTaskType() {
    return taskType;
  }


  protected void statusChange(EventStatus status) {
    switch (status) {
      case INCOMPLETE -> this.eventStatus = EventStatus.IN_PROGRESS;
      case IN_PROGRESS -> this.eventStatus = EventStatus.COMPLETE;

    }
  }

  public EventStatus getStatus() {
    return eventStatus;
  }


  abstract public void run();
}
