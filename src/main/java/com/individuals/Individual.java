package main.java.com.individuals;

import main.java.com.individuals.task.TaskObserver;
import main.java.com.item.Item;
import main.java.com.store.Store;



public interface Individual extends
    TaskObserver<Store, Individual, Item> {

}
