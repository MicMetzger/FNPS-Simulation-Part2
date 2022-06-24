package main.java.com.individuals;

import main.java.com.events.*;



public interface Individual extends EventObserver {
  
  void setName(String name);

  String getName();
}
