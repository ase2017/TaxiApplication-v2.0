package main.model;

import main.log.LoggerSingleton;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Class that represent the Taxi queue
 * @author George C. and Jules
 */
public class TaxiQueue extends Observable{

    LinkedList<Taxi> taxisQueue = new LinkedList<>();

    public LinkedList<Taxi> getTaxisQueue() {
        return taxisQueue;
    }

    public void setTaxisQueue(LinkedList<Taxi> taxisQueue) {
        this.taxisQueue = taxisQueue;
        setChanged();
        notifyObservers();
    }

    /**
     * Adds a taxi in the queue IF it does not already exist in the queue
     * if the queue is null, creates a new queue before adding
     * @param taxi
     */
    public void add(Taxi taxi) {
        if (taxi != null) {

            taxi.setArrivalTime(System.currentTimeMillis()); // queue arrival time

            if (taxisQueue == null){
                taxisQueue = new LinkedList<>();
                taxisQueue.add(taxi);
                setChanged();
                notifyObservers();

            } else {

                if (!containsTaxi(taxi)){
                    taxisQueue.add(taxi);
                    setChanged();
                    notifyObservers();
                }
            }


        }

    }

    /**
     * Gets and returns the first taxi in the queue, and removes it from the queue
     * @return the Taxi or null
     */
    public synchronized  Taxi popTaxi() {



        if(taxisQueue != null && taxisQueue.size() > 0) {

            Taxi taxi = taxisQueue.get(0);
            taxisQueue.removeFirst();

            if (taxisQueue.size() == 0){
                LoggerSingleton.getInstance().add("No more taxis");
            }

            taxi.setQueueDepartureTime(System.currentTimeMillis()); // queue departure time
            notifyObservers();
            return taxi;

        }

        return null;
    }


    /**
     * Returns true if a taxi with the same registration number exists
     * @param taxi
     * @return
     */
    public boolean containsTaxi(Taxi taxi) {

        if(taxi != null && taxisQueue != null) {

            for (Taxi tx : taxisQueue){

                if(taxi.getTaxiRegistrationNumber().equals(tx.getTaxiRegistrationNumber())) {
                    return  true;
                }
            }
        }

        return false;
    }
}
