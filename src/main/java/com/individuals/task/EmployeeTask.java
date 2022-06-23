package main.java.com.individuals.task;

public abstract class EmployeeTask {
  private   TaskType   taskType;
  protected TaskStatus status;



  protected enum TaskType {
    TASK_SELLING("Selling"),
    TASK_OPENING("Opening"),
    TASK_CLOSING("Closing"),
    TASK_PROCESSING("Processing"),
    TASK_INVENTORY("Inventory"),
    TASK_TRAINING("Training"),
    TASK_CLEANING("Cleaning"),
    TASK_BANKING("Banking"),
    TASK_FEEDING("Feeding");

    private String getName() {
      return name;
    }

    private final String name;

    TaskType(String name) {
      this.name = name;
    }
  }



  protected enum TaskStatus {
    INCOMPLETE("Incomplete"),
    COMPLETE("Complete"),
    ACTIVE("Active"),
    CANCELED("Canceled");

    private final String name;

    TaskStatus(String name) {
      this.name = name;
    }

    private String getName() {
      return name;
    }
  }


  public EmployeeTask(TaskType taskType) {
    this.taskType = taskType;
    this.status   = TaskStatus.ACTIVE;
  }

  public EmployeeTask(TaskType taskType, TaskStatus status) {
    this.taskType = taskType;
    this.status   = status;
  }

  public TaskType getTaskType() {
    return taskType;
  }


  private void statusChange(String status) {
    switch (TaskStatus.valueOf(status)) {
      case INCOMPLETE -> this.status = TaskStatus.ACTIVE;
      case ACTIVE -> this.status = TaskStatus.COMPLETE;
      case COMPLETE -> this.status = TaskStatus.CANCELED;
      case CANCELED -> this.status = TaskStatus.INCOMPLETE;
    }
  }
}
