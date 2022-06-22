package main.java.com.individuals.task;


public interface EventObserver {

  void update(EventObservable watched, Object event);
}
