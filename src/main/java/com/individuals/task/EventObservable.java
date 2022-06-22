package main.java.com.individuals.task;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @param <Sub>  Subject
 * @param <Obs>  Observer
 * @param <Argu> Argument type
 */
public interface EventObservable {

  void addObserver(EventObserver observer);

  void removeObserver(EventObserver observer);

  void warnObservers(Object argument);

}
