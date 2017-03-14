package main.model;


import java.util.Observable;
import java.util.Random;

public class Window extends Observable implements Runnable{

    private GroupOfPassengers groupOfPassengers;
    private Taxi taxi;
    private String status;
    private TaxiData taxiData;
    private int windowNumber;

    Random random = new Random();

    public int getIntBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run() {

        while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getTaxiQueue().getTaxisQueue().size() > 0) {

            System.out.println("Window " + windowNumber + " serving a new group");
            System.out.println("Number of groups : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            System.out.println("Number of taxis : " + taxiData.getTaxiQueue().getTaxisQueue().size());

            pickGroup();

            pickTaxi();


            groupAndTaxiLeaves();


            System.out.println("Window " + windowNumber + " finished serving a group");
            System.out.println("Number of groups : " + taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            System.out.println("Number of taxis : " + taxiData.getTaxiQueue().getTaxisQueue().size());
        }
    }



    public void pickGroup() {
        //TODO : setGroupOfPassengers(popGroup()); //SYNC


        setStatus(Status.BUSY.getString());

        GroupOfPassengers temporaryGroupOfPassengers = taxiData.getPassengerQueue().popGroup();

        // simulates the time for the groups of passengers to arrive
        try{
            Thread.sleep(getIntBetween(1000,3000));
        } catch (InterruptedException e) {

        }

        groupOfPassengers = temporaryGroupOfPassengers;


    }

    public void pickTaxi(){

        // simulates the time for the taxi to arrive at the window
        Taxi temporaryTaxi = taxiData.getTaxiQueue().popTaxi();

        try{
            Thread.sleep(getIntBetween(1000,3000));
        } catch (InterruptedException e) {

        }
        taxi = temporaryTaxi;

    }

    public void groupAndTaxiLeaves(){


        setTaxi(null);
        setGroupOfPassengers(null);

        // simulates the time between each  group allocation
        try{
            Thread.sleep(getIntBetween(1000,3000));
        } catch (InterruptedException e) {

        }

        setStatus(Status.AVAILABLE.getString());
    }


    private enum Status {
        BREAK("BREAK"), AVAILABLE("AVAILABLE"), BUSY("BUSY");
        private String status;

        Status(String s) {
            status = s;
        }
        public String getString() {
            return status;
        }
        public boolean containsStatus(String string) {
           for(Status status : Status.values()){
               if(status.equals(string))
                   return true;
           }
            return false;
        }

    }

    public Window(TaxiData taxiData, int i) {
        groupOfPassengers = null;
        taxi = null;
        this.status = Status.AVAILABLE.getString();
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
}
