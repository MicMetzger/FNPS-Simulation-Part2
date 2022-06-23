package main.java.com.individuals.task;

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
