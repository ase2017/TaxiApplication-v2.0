package main.model;

import java.util.Observable;

/**
 * @author George C. and Jules
 */
public class PassengerQueue extends Observable {


    public synchronized void popGroup(){
        notifyObservers();
    }
}
