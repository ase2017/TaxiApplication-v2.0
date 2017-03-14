package main.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;

/**
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


    public void add(GroupOfPassengers groupOfPassengers){
        if(groupOfPassengers != null){
            groupOfPassengers.setArrivalTime(System.currentTimeMillis());
            groupOfPassengersQueue.add(groupOfPassengers);
        }
    }

    public synchronized GroupOfPassengers popGroup(){

        if(groupOfPassengersQueue.size() > 0) {
            GroupOfPassengers groupOfPassengers = groupOfPassengersQueue.get(0);
            groupOfPassengersQueue.removeFirst();
            //notifyObservers();
            return groupOfPassengers;
        }

        return null;

    }


}
