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
/*
    public void generateAndAddTaxi() {

        Taxi tx;
        boolean taxiIsValid = false;
        while(!taxiIsValid) {
            tx = TaxiGenerator.generateTaxi();

            // does not exist in list
           /* if (!taxiList.containsTaxi(tx.getTaxiRegistrationNumber())) {
                // add it to list and queue
                taxiIsValid = true;
            } else {

                // exists in list but not in queue
                if(!taxiQueue.containsTaxi(tx.getTaxiRegistrationNumber())) {
                    // reject if reg number exists
                    if() {

                    }
                    // else : add it to queue
                    else {
                        taxiIsValid = true;
                    }

                }

               // exists in list and in queue
                else{
                    reject
                }

            }



        }

    }
*/




    public void addGroup() {

        //taxiQueue.add(GroupOfPassengersGenerator.generateGroupOfPassengers());
    }

    /**
     *
     * @param numberOfTaxis
     */
    public void fillTaxiQueue(int numberOfTaxis) {

        if (numberOfTaxis > 0) {



            for(int i = 0; i < numberOfTaxis; i++) {
                taxiQueue.getTaxisQueue().add(TaxiGenerator.generateTaxi());
                //taxiList.add(TaxiGenerator.generateTaxi(numberOfTaxis));
                // TODO : add to list
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
