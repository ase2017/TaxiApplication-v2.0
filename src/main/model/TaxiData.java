package main.model;

import java.util.Observable;

/**
 * Class that contains everything concerning Taxis
 * @author George C. and Jules
 */
public class TaxiData extends Observable{

    private TaxiList taxiList;
    private TaxiQueue taxiQueue;
    private PassengerQueue passengerQueue;

    public TaxiData(){

    }


    public TaxiData(int numberOfTaxis, int numberOfGroups) {

        taxiList = new TaxiList();
        taxiQueue = new TaxiQueue();
        passengerQueue = new PassengerQueue();
        fillTaxiQueue(numberOfTaxis);
        fillGroupsQueue(numberOfGroups);
    }

    /* ******************************************************************** */
    /* ************************ GUI METHODS ******************************* */
    /* ******************************************************************** */
    /**
     * Generates a random Taxi and adds it to the queue
     */
    public void generateAndAddTaxi() {

        Taxi taxi;
        boolean taxiIsValid = false;

        while(!taxiIsValid) {
            taxi = TaxiGenerator.generateTaxi();
            taxiIsValid = addTaxi(taxi);
        }

    }


    /**
     * Generates a random group of passengers and adds it to the queue
     */
    public void generateAndAddGroup() {

        if (passengerQueue == null){
            passengerQueue = new PassengerQueue();
        }

        passengerQueue.add(GroupOfPassengersGenerator.generateGroupOfPassengers());
    }

    /* ******************************************************************** */
    /* ******************************************************************** */
    /* ******************************************************************** */


    /**
     * Adds one taxi
     * @param taxi the Taxi to be added
     * @return true if the taxi was successfully added, else returns false
     */
    public boolean addTaxi(Taxi taxi){

        if(taxi != null) {

            if(taxiQueue == null){
                taxiQueue = new TaxiQueue();
            }

            if(taxiList == null){
                taxiList = new TaxiList();
            }


            // does not exist in list (i.e. a taxi with the same registration number has not already been created)
            if (!taxiList.containsTaxi(taxi)) {
                // add it to list and queue
                taxiQueue.add(taxi);
                taxiList.add(taxi);

                return  true;


            // exists in list (i.e. a taxi with the same registration number has already been created)
            } else {

                //  does not exist in queue
                if(!taxiQueue.containsTaxi(taxi)) {
                    taxiQueue.add(taxi);
                    return true;
                }
            }
        }
        return  false;

    }





    /**
     * Adds n taxis in the queue
     * @param numberOfTaxis the number of taxis to be added
     */
    public void fillTaxiQueue(int numberOfTaxis) {

        if (numberOfTaxis > 0) {

            for(int i = 0; i < numberOfTaxis; i++) {
                generateAndAddTaxi();
            }

        } else {

            if (taxiList == null){
                taxiList = new TaxiList();
            }

            if (taxiQueue == null){
                taxiQueue = new TaxiQueue();
            }

        }
    }

    /**
     * Adds n groups of passengers if n > 0
     * @param numberOfGroups the number of passengers to be added
     */
    public void fillGroupsQueue(int numberOfGroups) {



        if (numberOfGroups > 0) {

            for(int i = 0; i < numberOfGroups; i++) {
                generateAndAddGroup();
            }

        } else {

            if(passengerQueue == null){
                passengerQueue = new PassengerQueue();
            }
        }
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
