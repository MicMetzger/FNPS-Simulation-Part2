package main.java.com.individuals.task;


public interface TaskObserver<Sub extends TaskObservable<Sub, Obs, Argu>, Obs extends TaskObserver<Sub, Obs, Argu>, Argu> {

  void update(Sub subject, Argu arg);
}
