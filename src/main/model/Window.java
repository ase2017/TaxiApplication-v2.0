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


    @Override
    public void run() {


        while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() > 0
                && !stopped) {

                pickGroup();

                if (groupOfPassengers != null) {
                    while (taxiData.getTaxiQueue().getTaxisQueue().size() > 0 && remainingNumberOfPassengers != 0) {
                        pickTaxi();
                        partOfGroupLeaves();
                    }

                    if (remainingNumberOfPassengers == 0)
                        allGroupLeft();
                }

        }

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


        setWorkingEndTime(System.currentTimeMillis());


    }


    public boolean isUnavailable(){
        return (stopped || taxiData.getTaxiQueue().getTaxisQueue().size() == 0
                || taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 0);
    }


    /**
     * function to pick a group : gets a group, simulates the group coming, and sets the group
     */
    public void pickGroup() {

        setStatus(WindowStatuses.BUSY.toString());

        GroupOfPassengers temporaryGroupOfPassengers = taxiData.getPassengerQueue().popGroup();

        // simulates the time for the groups of passengers to arrive
        try{
            Thread.sleep(Utils.getIntBetween(2000,2000));
        } catch (InterruptedException e) {

        }




        setGroupOfPassengers(temporaryGroupOfPassengers);

        if (temporaryGroupOfPassengers != null) {

            setRemainingNumberOfPassengers(temporaryGroupOfPassengers.getNumberOfPassengers());
        }

    }

    /**
     * function to pick a Taxi : gets a Taxi, simulates the taxi coming, and sets the Taxi
     */
    public void pickTaxi(){


        // simulates the time for the taxi to arrive at the window
        Taxi temporaryTaxi = taxiData.getTaxiQueue().popTaxi();

        try{
            Thread.sleep(Utils.getIntBetween(2000,2000));
        } catch (InterruptedException e) {

        }

        setTaxi(temporaryTaxi);
        
        LoggerSingleton.getInstance().addRecord(this.getWindowNumber(),
                this.getGroupOfPassengers().getDestinationName(),
                this.getGroupOfPassengers().getNumberOfPassengers(),
                this.getRemainingNumberOfPassengers(),
                this.getTaxi().getTaxiRegistrationNumber());

    }

    /**
     * function to call to simulate that some passengers of a group left
     */
    public void partOfGroupLeaves(){


        // simulates the people leaving
        try{
            Thread.sleep(Utils.getIntBetween(4000,4000));
        } catch (InterruptedException e) {

        }


        if(taxi != null) {
            int servedNumberOfPassengersThisTime = remainingNumberOfPassengers - taxi.getMaximumNumberOfPassengers();

            if (  servedNumberOfPassengersThisTime < 0) {
                setTotalNumberOfPassengersServed(totalNumberOfPassengersServed + taxi.getMaximumNumberOfPassengers());
                setRemainingNumberOfPassengers(0);
            } else {
                setTotalNumberOfPassengersServed(totalNumberOfPassengersServed + servedNumberOfPassengersThisTime);
                setRemainingNumberOfPassengers(remainingNumberOfPassengers - taxi.getMaximumNumberOfPassengers());
            }
        }

        setTotalNumberOfAllocatedTaxis(totalNumberOfAllocatedTaxis + 1);
        setTaxi(null);

        // simulates the time between each  group allocation
        try{
            Thread.sleep(Utils.getIntBetween(2000,2000));
        } catch (InterruptedException e) {

        }

    }


    /**
     * function to call to simulate that all passengers of a group left
     */
    public void allGroupLeft(){

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
        if(isUnavailable()) {
            setStatus(WindowStatuses.UNAVAILABLE.toString());
        } else {
            setStatus(WindowStatuses.AVAILABLE.toString());
        }


        try{
            Thread.sleep(Utils.getIntBetween(4000,4000));
        } catch (InterruptedException e) {

        }

        setTotalNumberOfGroupsServed(totalNumberOfGroupsServed + 1);

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
        //setChanged();
        //notifyObservers();
    }

    public int getTotalNumberOfGroupsServed() {
        return totalNumberOfGroupsServed;
    }

    public void setTotalNumberOfGroupsServed(int totalNumberOfGroupsServed) {
        this.totalNumberOfGroupsServed = totalNumberOfGroupsServed;
        //setChanged();
        //notifyObservers();
    }

    public int getTotalNumberOfAllocatedTaxis() {
        return totalNumberOfAllocatedTaxis;
    }

    public void setTotalNumberOfAllocatedTaxis(int totalNumberOfAllocatedTaxis) {
        this.totalNumberOfAllocatedTaxis = totalNumberOfAllocatedTaxis;
        //setChanged();
        //notifyObservers();
    }

    public long getWorkingStartTime() {
        return workingStartTime;
    }

    public void setWorkingStartTime(long workingStartTime) {
        this.workingStartTime = workingStartTime;
        //setChanged();
        //notifyObservers();
    }

    public long getWorkingEndTime() {
        return workingEndTime;
    }

    public void setWorkingEndTime(long workingEndTime) {
        this.workingEndTime = workingEndTime;
        //setChanged();
        //notifyObservers();
    }

    public int getSmallestGroupSizeServed() {
        return smallestGroupSizeServed;
    }

    public void setSmallestGroupSizeServed(int smallestGroupSizeServed) {
        this.smallestGroupSizeServed = smallestGroupSizeServed;
        //setChanged();
        //notifyObservers();
    }

    public int getBiggestGroupSizeServed() {
        return biggestGroupSizeServed;
    }

    public void setBiggestGroupSizeServed(int biggestGroupSizeServed) {
        this.biggestGroupSizeServed = biggestGroupSizeServed;
        //setChanged();
        //notifyObservers();
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped() {
        this.stopped = true;

    }





}
