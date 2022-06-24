package main.java.com.events;


public interface EventObserver {
  // void run(Object event);
  void update(EventObservable watched, Object event);
}
