package main.java.com.staff.task;

// TODO:
public class TaskContext {
  Task task;
  
  TaskContext(Task task) {
    this.task = task;
  }
  
  void action() {
    this.task.execute();
  }
  
}
