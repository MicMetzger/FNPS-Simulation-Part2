package main.java.com.events;

/**
 * @param <Sub>  Subject
 * @param <Obs>  Observer
 * @param <Argu> Argument type
 */
public interface EventObservable {

  void addObserver(EventObserver observer);

  void removeObserver(EventObserver observer);

  void notifyObservers(Object argument);

}
