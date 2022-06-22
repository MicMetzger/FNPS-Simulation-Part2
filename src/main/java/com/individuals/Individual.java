package main.java.com.individuals;

import main.java.com.individuals.task.EventObserver;



public interface Individual extends EventObserver {
  void setName(String name);

  String getName();
}
