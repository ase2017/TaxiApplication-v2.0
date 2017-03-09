package main.model;

import java.util.LinkedList;
import java.util.Observable;

/**
 * @author George C. and Jules
 */
public class TaxiQueue extends Observable{

    LinkedList<Taxi> taxisQueue = new LinkedList<>();

    public LinkedList<Taxi> getTaxisQueue() {
        return taxisQueue;
    }

    public void setTaxisQueue(LinkedList<Taxi> taxisQueue) {
        this.taxisQueue = taxisQueue;
    }

    public synchronized  Taxi popTaxi() {
        if(taxisQueue.size() > 0) {
            Taxi taxi = taxisQueue.get(0);
            taxisQueue.removeFirst();
            //notifyObservers();
            return taxi;
        }

        return null;
    }
}
