package main.model;

import java.util.Observable;

/**
 * @author George C. and Jules
 */
public class TaxiData extends Observable implements  Runnable{

    private TaxiGenerator taxiGenerator = new TaxiGenerator();
    private GroupOfPassengersGenerator groupOfPassengersGenerator = new GroupOfPassengersGenerator();

    private TaxiList taxiList;
    private TaxiQueue taxiQueue;
    private PassengerQueue passengerQueue;


    public TaxiData(int numberOfTaxis, int numberOfGroups) {



        // taxis
        taxiList = new TaxiList();
        taxiQueue = new TaxiQueue();
        passengerQueue = new PassengerQueue();
        fillTaxiQueue(numberOfTaxis);
        fillGroupsQueue(numberOfGroups);
    }

    /**
     * Generates one taxi
     */
    public void generateAndAddTaxi() {

        Taxi tx;
        boolean taxiIsValid = false;

        while(!taxiIsValid) {
            tx = TaxiGenerator.generateTaxi();

            // does not exist in list
            if (!taxiList.containsTaxi(tx)) {
                // add it to list and queue
                taxiIsValid = true;
                taxiQueue.getTaxisQueue().add(tx);
                taxiList.add(tx);


            // exists in list
            } else {

                //  does not exist in queue
                if(!taxiQueue.containsTaxi(tx)) {
                    taxiQueue.getTaxisQueue().add(tx);
                    taxiIsValid = true;
                }
            }

        }

    }




    public void addGroup() {

        passengerQueue.add(GroupOfPassengersGenerator.generateGroupOfPassengers());
    }

    /**
     *
     * @param numberOfTaxis
     */
    public void fillTaxiQueue(int numberOfTaxis) {

        if (numberOfTaxis > 0) {

            for(int i = 0; i < numberOfTaxis; i++) {
                generateAndAddTaxi();
            }

        }
    }

    public void fillGroupsQueue(int numberOfGroups) {

        if (numberOfGroups > 0) {
            for(int i = 0; i < numberOfGroups; i++) {
                passengerQueue.getGroupOfPassengersQueue().add(GroupOfPassengersGenerator.generateGroupOfPassengers());
            }
        }
    }

    public void run() {

    }

    public TaxiGenerator getTaxiGenerator() {
        return taxiGenerator;
    }

    public void setTaxiGenerator(TaxiGenerator taxiGenerator) {
        this.taxiGenerator = taxiGenerator;
    }

    public GroupOfPassengersGenerator getGroupOfPassengersGenerator() {
        return groupOfPassengersGenerator;
    }

    public void setGroupOfPassengersGenerator(GroupOfPassengersGenerator groupOfPassengersGenerator) {
        this.groupOfPassengersGenerator = groupOfPassengersGenerator;
    }

    public TaxiList getTaxiList() {
        return taxiList;
    }

    public void setTaxiList(TaxiList taxiList) {
        this.taxiList = taxiList;
    }

    public TaxiQueue getTaxiQueue() {
        return taxiQueue;
    }

    public void setTaxiQueue(TaxiQueue taxiQueue) {
        this.taxiQueue = taxiQueue;
    }

    public PassengerQueue getPassengerQueue() {
        return passengerQueue;
    }

    public void setPassengerQueue(PassengerQueue passengerQueue) {
        this.passengerQueue = passengerQueue;
    }
}
