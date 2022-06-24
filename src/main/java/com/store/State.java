package main.java.com.store;

import main.java.com.events.task.*;
import main.java.com.individuals.*;



public interface State {

  void enterState();

  void exitState();

  void nextState();

  boolean hasTask();

  EmployeeTask getTask();

  EmployeeTask getTask(Employee employee);
}
