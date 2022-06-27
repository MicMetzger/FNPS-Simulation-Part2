package main.java.com.individuals;

import main.java.com.events.*;



public interface Individual extends EventObserver {

  String getNameSimple();

  void setName(String name);

  String getName();
}
