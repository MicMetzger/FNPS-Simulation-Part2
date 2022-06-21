package main.java.com.individuals.task;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @param <Sub>  Subject
 * @param <Obs>  Observer
 * @param <Argu> Argument type
 */
public abstract class TaskObservable<Sub extends TaskObservable<Sub, Obs, Argu>, Obs extends TaskObserver<Sub, Obs, Argu>, Argu> {

  protected final List<Obs> observers;

  protected TaskObservable() {
    this.observers = new CopyOnWriteArrayList<>();
  }

  public void callout(Argu argument) {
    observers.forEach(observer -> observer.update((Sub) this, argument));
  }

}
