package main.model;


import main.Main;

import java.util.Observable;
import java.util.Random;

public class Window extends Observable implements Runnable{

    private GroupOfPassengers groupOfPassengers;
    private Taxi taxi;
    private String status;
    private TaxiData taxiData;
    private int windowNumber;
    private int remainingNumberOfPassengers;
    private int numberOfPassengersServed = 0;
    private int numberOfGroupsServed = 0;
    private long timeWorked = 0;


    Random random = new Random();

    public int getIntBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run() {

        // TODO : go to break after 2 minutes for example


        while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() > 0 ) {

           System.out.println("\nA Window " + windowNumber + " serving a new group");
          // System.out.println("B Window " + windowNumber + "Number of groups : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            //System.out.println("C Window " + windowNumber + "Number of taxis : " + taxiData.getTaxiQueue().getTaxisQueue().size());


            pickGroup();


            while (groupOfPassengers!= null && taxiData.getTaxiQueue().getTaxisQueue().size() > 0){
               // System.out.println("\n1 INSIDE NESTED WHILE : Window " + windowNumber + " remaining number of passengers " + remainingNumberOfPassengers);
                pickTaxi();
               // System.out.println("\n2 INSIDE NESTED WHILE : Window " + windowNumber + " remaining number of passengers " + remainingNumberOfPassengers);

               // System.out.println("INSIDE NESTED WHILE : Window " + windowNumber + " taxi size" + taxi.getMaximumNumberOfPassengers());
                partOfGroupLeaves();
               // System.out.println("3 INSIDE NESTED WHILE : Window " + windowNumber + " remaining number of passengers" + remainingNumberOfPassengers);
            }

            if (remainingNumberOfPassengers == 0)
                allGroupLeft();



            //System.out.println("\n? AFTER NESTED WHILE : Window " + windowNumber + " finished serving a group");
            //System.out.println("! AFTER NESTED WHILE : Window " + windowNumber + " Number of groups : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            //System.out.println("@ AFTER NESTED WHILE : Window " + windowNumber +" Number of taxis : " + taxiData.getTaxiQueue().getTaxisQueue().size());


        }


    }





    public void pickGroup() {

        setStatus(WindowStatuses.BUSY.toString());


        GroupOfPassengers temporaryGroupOfPassengers = taxiData.getPassengerQueue().popGroup();

        if (temporaryGroupOfPassengers != null){
            remainingNumberOfPassengers = temporaryGroupOfPassengers.getNumberOfPassengers();

            // simulates the time for the groups of passengers to arrive
            try{
                Thread.sleep(getIntBetween(1000,3000));
            } catch (InterruptedException e) {

            }


        }

        setGroupOfPassengers(temporaryGroupOfPassengers);


    }

    public void pickTaxi(){


        // simulates the time for the taxi to arrive at the window
        Taxi temporaryTaxi = taxiData.getTaxiQueue().popTaxi();

        try{
            Thread.sleep(getIntBetween(1000,3000));
        } catch (InterruptedException e) {

        }

        setTaxi(temporaryTaxi);

    }

    public void partOfGroupLeaves(){


        if(taxi != null) {
            if ( remainingNumberOfPassengers - taxi.getMaximumNumberOfPassengers() < 0) {
                setRemainingNumberOfPassengers(0);
            } else {
                setRemainingNumberOfPassengers(remainingNumberOfPassengers - taxi.getMaximumNumberOfPassengers());
            }
        }

        setTaxi(null);

        // simulates the time between each  group allocation
        try{
            Thread.sleep(getIntBetween(1000,3000));
        } catch (InterruptedException e) {

        }


    }


    public void allGroupLeft(){

        setGroupOfPassengers(null);
        setStatus(WindowStatuses.AVAILABLE.toString());

        try{
            Thread.sleep(getIntBetween(1000,5000));
        } catch (InterruptedException e) {

        }

    }

    public Window(TaxiData taxiData, int i) {
        groupOfPassengers = null;
        taxi = null;
        this.status = WindowStatuses.AVAILABLE.toString();
        this.taxiData = taxiData;
        windowNumber = i;

    }

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
}
