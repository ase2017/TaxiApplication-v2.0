package main.model;

import java.util.LinkedList;
import java.util.Observable;

/**
 * Class that represents the queue of passengers
 * @author George C. and Jules
 */
public class PassengerQueue extends Observable {


    LinkedList<GroupOfPassengers> groupOfPassengersQueue = new LinkedList<>();


    public LinkedList<GroupOfPassengers> getGroupOfPassengersQueue() {
        return groupOfPassengersQueue;
    }

    public void setGroupOfPassengersQueue(LinkedList<GroupOfPassengers> groupOfPassengersQueue) {
        this.groupOfPassengersQueue = groupOfPassengersQueue;
    }


    /**
     * adds a new group in the queue
     * @param groupOfPassengers
     */
    public void add(GroupOfPassengers groupOfPassengers){

        if(groupOfPassengers != null){

            if(groupOfPassengersQueue == null){
                groupOfPassengersQueue = new LinkedList<>();
            }

            groupOfPassengers.setArrivalTime(System.currentTimeMillis()); // queue arrival time
            groupOfPassengersQueue.add(groupOfPassengers);
            setChanged();
            notifyObservers();
        }

    }

    /**
     * Gets and returns the first group in the queue, and removes it from the queue
     * @return the group or null
     */
    public synchronized GroupOfPassengers popGroup(){

        if( groupOfPassengersQueue != null && groupOfPassengersQueue.size() > 0) {
            GroupOfPassengers groupOfPassengers = groupOfPassengersQueue.get(0);
            groupOfPassengersQueue.removeFirst();
            groupOfPassengers.setQueueDepartureTime(System.currentTimeMillis()); // queue departure time
            setChanged();
            notifyObservers();
            return groupOfPassengers;
        }

        return null;

    }


}
