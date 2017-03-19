package main.model;

import java.util.Observable;
import java.util.Random;

/**
 * Class that represents a window where the passengers will be called
 */
public class Window extends Observable implements Runnable{

    private GroupOfPassengers groupOfPassengers;
    private Taxi taxi;
    private String status;
    private TaxiData taxiData;

    private int windowNumber;
    private int remainingNumberOfPassengers;
    private int totalNumberOfPassengersServed = 0;
    private int totalNumberOfGroupsServed = 0;
    private int totalNumberOfAllocatedTaxis = 0;
    private int smallestGroupSizeServed = 0;
    private int biggestGroupSizeServed = 0;
    private long workingStartTime = 0;
    private long workingEndTime = 0;



    Random random = new Random();

    public int getIntBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run() {


        while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() > 0 ) {

           System.out.println("\nWindow " + windowNumber + " serving a new group");
          // System.out.println("B Window " + windowNumber + "Number of groups : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            //System.out.println("C Window " + windowNumber + "Number of taxis : " + taxiData.getTaxiQueue().getTaxisQueue().size());


            pickGroup();

            if(groupOfPassengers != null) {
                while (taxiData.getTaxiQueue().getTaxisQueue().size() > 0 && remainingNumberOfPassengers != 0){
                    // System.out.println("\n1 INSIDE NESTED WHILE : Window " + windowNumber + " remaining number of passengers " + remainingNumberOfPassengers);
                    pickTaxi();
                    // System.out.println("\n2 INSIDE NESTED WHILE : Window " + windowNumber + " remaining number of passengers " + remainingNumberOfPassengers);

                    // System.out.println("INSIDE NESTED WHILE : Window " + windowNumber + " taxi size" + taxi.getMaximumNumberOfPassengers());
                    partOfGroupLeaves();
                    // System.out.println("3 INSIDE NESTED WHILE : Window " + windowNumber + " remaining number of passengers" + remainingNumberOfPassengers);
                }



                if (remainingNumberOfPassengers == 0)
                    allGroupLeft();
            }





            //System.out.println("\n? AFTER NESTED WHILE : Window " + windowNumber + " finished serving a group");
            //System.out.println("! AFTER NESTED WHILE : Window " + windowNumber + " Number of groups : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            //System.out.println("@ AFTER NESTED WHILE : Window " + windowNumber +" Number of taxis : " + taxiData.getTaxiQueue().getTaxisQueue().size());


        }

        System.out.println("Total number of groups served by window " + windowNumber + " : " + totalNumberOfGroupsServed);
        System.out.println("Total number of passengers served by window " + windowNumber + " : " + totalNumberOfPassengersServed);
        System.out.println("Total remaining number of passengers in window " + windowNumber + " : " + remainingNumberOfPassengers);
        System.out.println("Total number of taxis allocated by window " + windowNumber + " : " + totalNumberOfAllocatedTaxis);
        System.out.println("Smallest group size served by window " + windowNumber + " : " + smallestGroupSizeServed);
        System.out.println("Biggest group size served by window " + windowNumber + " : " + biggestGroupSizeServed);
        System.out.println("Remaining number of groups from window " + windowNumber + " : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
        System.out.println("Remaining number of taxis from window " + windowNumber + " : " + taxiData.getTaxiQueue().getTaxisQueue().size() + "\n");


        setWorkingEndTime(System.currentTimeMillis());


    }




    /**
     * function to pick a group : gets a group, simulates the group coming, and sets the group
     */
    public void pickGroup() {

        setStatus(WindowStatuses.BUSY.toString());


        GroupOfPassengers temporaryGroupOfPassengers = taxiData.getPassengerQueue().popGroup();

        if (temporaryGroupOfPassengers != null){

            setRemainingNumberOfPassengers(temporaryGroupOfPassengers.getNumberOfPassengers());

            // simulates the time for the groups of passengers to arrive
            try{
                Thread.sleep(getIntBetween(1000,3000));
            } catch (InterruptedException e) {

            }


        }

        setGroupOfPassengers(temporaryGroupOfPassengers);


    }

    /**
     * function to pick a Taxi : gets a Taxi, simulates the taxi coming, and sets the Taxi
     */
    public void pickTaxi(){


        // simulates the time for the taxi to arrive at the window
        Taxi temporaryTaxi = taxiData.getTaxiQueue().popTaxi();

        try{
            Thread.sleep(getIntBetween(1000,3000));
        } catch (InterruptedException e) {

        }

        setTaxi(temporaryTaxi);

    }

    /**
     * function to call to simulate that some passengers of a group left
     */
    public void partOfGroupLeaves(){


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
            Thread.sleep(getIntBetween(1000,3000));
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
        setStatus(WindowStatuses.AVAILABLE.toString());

        try{
            Thread.sleep(getIntBetween(1000,5000));
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
        notifyObservers();
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
        notifyObservers();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public int getRemainingNumberOfPassengers() {
        return remainingNumberOfPassengers;
    }

    public void setRemainingNumberOfPassengers(int remainingNumberOfPassengers) {
        this.remainingNumberOfPassengers = remainingNumberOfPassengers;
        notifyObservers();
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
        notifyObservers();
    }

    public int getTotalNumberOfGroupsServed() {
        return totalNumberOfGroupsServed;
    }

    public void setTotalNumberOfGroupsServed(int totalNumberOfGroupsServed) {
        this.totalNumberOfGroupsServed = totalNumberOfGroupsServed;
        notifyObservers();
    }

    public int getTotalNumberOfAllocatedTaxis() {
        return totalNumberOfAllocatedTaxis;
    }

    public void setTotalNumberOfAllocatedTaxis(int totalNumberOfAllocatedTaxis) {
        this.totalNumberOfAllocatedTaxis = totalNumberOfAllocatedTaxis;
        notifyObservers();
    }

    public long getWorkingStartTime() {
        return workingStartTime;
    }

    public void setWorkingStartTime(long workingStartTime) {
        this.workingStartTime = workingStartTime;
        notifyObservers();
    }

    public long getWorkingEndTime() {
        return workingEndTime;
    }

    public void setWorkingEndTime(long workingEndTime) {
        this.workingEndTime = workingEndTime;
        notifyObservers();
    }

    public int getSmallestGroupSizeServed() {
        return smallestGroupSizeServed;
    }

    public void setSmallestGroupSizeServed(int smallestGroupSizeServed) {
        this.smallestGroupSizeServed = smallestGroupSizeServed;
        notifyObservers();
    }

    public int getBiggestGroupSizeServed() {
        return biggestGroupSizeServed;
    }

    public void setBiggestGroupSizeServed(int biggestGroupSizeServed) {
        this.biggestGroupSizeServed = biggestGroupSizeServed;
        notifyObservers();
    }
}
