package main.model;

import main.log.LoggerSingleton;
import main.utils.Utils;
import java.util.Observable;

/**
 * Class that represents a window where the passengers will be called
 */
public class Window extends Observable implements Runnable{

    private GroupOfPassengers groupOfPassengers;
    private Taxi taxi;
    private String status;
    private TaxiData taxiData;
    private int windowNumber;


    private boolean stopped = false;

    //stats
    private int remainingNumberOfPassengers;
    private int totalNumberOfPassengersServed = 0;
    private int totalNumberOfGroupsServed = 0;
    private int totalNumberOfAllocatedTaxis = 0;
    private int smallestGroupSizeServed = 0;
    private int biggestGroupSizeServed = 0;
    private long workingStartTime = 0;
    private long workingEndTime = 0;

    private int SIMULATION_SPEED = 1;


    @Override
    public void run() {

        // keeps going while there are still taxis and passengers in the queues, and if the windows are not stopped ('end of day')
        while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() > 0
                && !stopped) {

                // pick a group of passengers
                pickGroup();

                // could be null because of multi threading so checking
                if (groupOfPassengers != null) {

                    // while the entire group has not been served (ie the taxi allocated did not have enough places)
                    // and while there are taxis in the queue, we pick a taxi to serve the remaining passengers of the group
                    while (taxiData.getTaxiQueue().getTaxisQueue().size() > 0 && remainingNumberOfPassengers != 0) {

                        pickTaxi(); // picks the taxi
                        partOfGroupLeaves(); // updates the remaining number of passengers and removes the taxi
                    }

                    if (remainingNumberOfPassengers == 0)
                        allGroupLeft(); // removes the group from the window if everybody in the current group left
                                        // also sets the status to available if the window has not been sent to "end of day"
                }

        }

        // when at least one queue is empty, the window stops and becomes "unavailable"
        setWorkingEndTime(System.currentTimeMillis()); // the time at which the window stopped working
        setStatus(WindowStatuses.UNAVAILABLE.toString());

        LoggerSingleton.getInstance().add("Closing window " + windowNumber);

        //System.out.println("Total number of groups served by window " + windowNumber + " : " + totalNumberOfGroupsServed);
        //System.out.println("Total number of passengers served by window " + windowNumber + " : " + totalNumberOfPassengersServed);
        //System.out.println("Total remaining number of passengers in window " + windowNumber + " : " + remainingNumberOfPassengers);
        //System.out.println("Total number of taxis allocated by window " + windowNumber + " : " + totalNumberOfAllocatedTaxis);
        //System.out.println("Smallest group size served by window " + windowNumber + " : " + smallestGroupSizeServed);
        //System.out.println("Biggest group size served by window " + windowNumber + " : " + biggestGroupSizeServed);
        //System.out.println("Remaining number of groups from window " + windowNumber + " : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
        //System.out.println("Remaining number of taxis from window " + windowNumber + " : " + taxiData.getTaxiQueue().getTaxisQueue().size() + "\n");

    }


    /**
     * checks if the window is unavailable or should be
     * @return
     */
    public boolean isUnavailable(){
        return (stopped || taxiData.getTaxiQueue().getTaxisQueue().size() == 0
                || taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 0);
    }


    /**
     * function to pick a group : gets a group, removes it from the queue,
     * simulates the group coming, and sets the group to the window
     */
    public void pickGroup() {

        // first thing, sets the window status to busy
        setStatus(WindowStatuses.BUSY.toString());

        // gets and removes the first group of passengers of the queue (if any)
        GroupOfPassengers temporaryGroupOfPassengers = taxiData.getPassengerQueue().popGroup();

        if (temporaryGroupOfPassengers != null) {

            // simulates the time for the groups of passengers to arrive (between 2 and 4 seconds)
            try{
                Thread.sleep(SIMULATION_SPEED * Utils.getIntBetween(2000,4000));
            } catch (InterruptedException e) {

            }

            // the group arrived at the window
            setGroupOfPassengers(temporaryGroupOfPassengers);

            // the remaining number of passengers to serve is the initial group size
            setRemainingNumberOfPassengers(temporaryGroupOfPassengers.getNumberOfPassengers());
        }

    }

    /**
     * function to pick a Taxi : gets a Taxi, simulates the taxi coming, and sets the Taxi
     */
    public void pickTaxi(){

        // gets and removes the first taxi of the queue (if any)
        Taxi temporaryTaxi = taxiData.getTaxiQueue().popTaxi();

        if (temporaryTaxi != null){

            // simulates the time for the taxi to arrive at the window (between 2 and 4 seconds)
            try{
                Thread.sleep(SIMULATION_SPEED * Utils.getIntBetween(2000,4000));
            } catch (InterruptedException e) {

            }

            // the taxi arrived at the window
            setTaxi(temporaryTaxi);

            //logging
            LoggerSingleton.getInstance().addRecord(this.getWindowNumber(),
                    this.getGroupOfPassengers().getDestinationName(),
                    this.getGroupOfPassengers().getNumberOfPassengers(),
                    this.getRemainingNumberOfPassengers(),
                    this.getTaxi().getTaxiRegistrationNumber());
        }


    }

    /**
     * function to call to simulate that some passengers of a group left
     */
    public void partOfGroupLeaves(){


        // simulates the people leaving (between 2 and 4 seconds)
        try{
            Thread.sleep(SIMULATION_SPEED * Utils.getIntBetween(2000,4000));
        } catch (InterruptedException e) {

        }


        if(taxi != null) {
            int servedNumberOfPassengersThisTime = remainingNumberOfPassengers - taxi.getMaximumNumberOfPassengers();

            // if there are more seats than passengers
            if (  servedNumberOfPassengersThisTime < 0) {
                setTotalNumberOfPassengersServed(totalNumberOfPassengersServed + taxi.getMaximumNumberOfPassengers());
                setRemainingNumberOfPassengers(0);
            // is there are less seats than passengers (or the same number)
            } else {
                setTotalNumberOfPassengersServed(totalNumberOfPassengersServed + servedNumberOfPassengersThisTime);
                setRemainingNumberOfPassengers(remainingNumberOfPassengers - taxi.getMaximumNumberOfPassengers());
            }
            // incrementing the number of taxis
            setTotalNumberOfAllocatedTaxis(totalNumberOfAllocatedTaxis + 1);

            setTaxi(null);

        }

        // simulates the time between each  group allocation (between 2 and 4 seconds)
        try{
            Thread.sleep(SIMULATION_SPEED * Utils.getIntBetween(2000,4000));
        } catch (InterruptedException e) {

        }

    }


    /**
     * function to call to simulate that all passengers of a group left
     */
    public void allGroupLeft(){

        if(groupOfPassengers != null){

            // departure time of the last person in the group
            groupOfPassengers.setFinalDepartureTime(System.currentTimeMillis());

            // updating biggest group size served
            if (groupOfPassengers.getNumberOfPassengers() > biggestGroupSizeServed){

                setBiggestGroupSizeServed(groupOfPassengers.getNumberOfPassengers());
            }

            // updating smallest group size served
            if(smallestGroupSizeServed == 0){
                setSmallestGroupSizeServed(groupOfPassengers.getNumberOfPassengers());
            } else {
                if (groupOfPassengers.getNumberOfPassengers() < smallestGroupSizeServed){
                    setSmallestGroupSizeServed(groupOfPassengers.getNumberOfPassengers());
                }
            }


            setGroupOfPassengers(null);
            setTotalNumberOfGroupsServed(totalNumberOfGroupsServed + 1);
        }

        // updating the status
        if(isUnavailable()) {
            setStatus(WindowStatuses.UNAVAILABLE.toString());
        } else {
            setStatus(WindowStatuses.AVAILABLE.toString());
        }

        // simulates the time during each group
        try{
            Thread.sleep(SIMULATION_SPEED * Utils.getIntBetween(4000,4000));
        } catch (InterruptedException e) {

        }



    }

    public Window(TaxiData taxiData, int i) {
        groupOfPassengers = null;
        taxi = null;
        this.status = WindowStatuses.AVAILABLE.toString();
        this.taxiData = taxiData;
        windowNumber = i;
        setWorkingStartTime(System.currentTimeMillis());

    }


    /* *************** GET SET ************************* */

    public TaxiData getTaxiData() {
        return taxiData;
    }

    public void setTaxiData(TaxiData taxiData) {
        this.taxiData = taxiData;
    }

    public GroupOfPassengers getGroupOfPassengers() {
        return groupOfPassengers;
    }

    public void setGroupOfPassengers(GroupOfPassengers groupOfPassengers) {
        this.groupOfPassengers = groupOfPassengers;
        setChanged();
        notifyObservers();
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
        setChanged();
        notifyObservers();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        System.out.println("NEW status of window " + windowNumber + " : " + status);
        this.status = status;
            setChanged();
            notifyObservers();


    }

    public int getRemainingNumberOfPassengers() {
        return remainingNumberOfPassengers;
    }

    public void setRemainingNumberOfPassengers(int remainingNumberOfPassengers) {
        int previousNumberOfPassengers = this.remainingNumberOfPassengers;
        this.remainingNumberOfPassengers = remainingNumberOfPassengers;
        if(previousNumberOfPassengers != this.remainingNumberOfPassengers){
            setChanged();
            notifyObservers();
        }

    }

    public int getWindowNumber() {
        return windowNumber;
    }

    public void setWindowNumber(int windowNumber) {
        this.windowNumber = windowNumber;
    }

    public int getTotalNumberOfPassengersServed() {
        return totalNumberOfPassengersServed;
    }

    public void setTotalNumberOfPassengersServed(int totalNumberOfPassengersServed) {
        this.totalNumberOfPassengersServed = totalNumberOfPassengersServed;

    }

    public int getTotalNumberOfGroupsServed() {
        return totalNumberOfGroupsServed;
    }

    public void setTotalNumberOfGroupsServed(int totalNumberOfGroupsServed) {
        this.totalNumberOfGroupsServed = totalNumberOfGroupsServed;
    }

    public int getTotalNumberOfAllocatedTaxis() {
        return totalNumberOfAllocatedTaxis;
    }

    public void setTotalNumberOfAllocatedTaxis(int totalNumberOfAllocatedTaxis) {
        this.totalNumberOfAllocatedTaxis = totalNumberOfAllocatedTaxis;
    }

    public long getWorkingStartTime() {
        return workingStartTime;
    }

    public void setWorkingStartTime(long workingStartTime) {
        this.workingStartTime = workingStartTime;
    }

    public long getWorkingEndTime() {
        return workingEndTime;
    }

    public void setWorkingEndTime(long workingEndTime) {
        this.workingEndTime = workingEndTime;
    }

    public int getSmallestGroupSizeServed() {
        return smallestGroupSizeServed;
    }

    public void setSmallestGroupSizeServed(int smallestGroupSizeServed) {
        this.smallestGroupSizeServed = smallestGroupSizeServed;
    }

    public int getBiggestGroupSizeServed() {
        return biggestGroupSizeServed;
    }

    public void setBiggestGroupSizeServed(int biggestGroupSizeServed) {
        this.biggestGroupSizeServed = biggestGroupSizeServed;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped() {
        this.stopped = true;

    }





}
