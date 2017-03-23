package main.model;

import java.util.ArrayList;

/**
 * Holds all the taxis created in the program.
 * Allows to avoid duplicate information in the queue (two taxis with the same registration number)
 * @author George C. and Jules
 */
public class TaxiList{

    ArrayList<Taxi> taxis = new ArrayList<>();

    /**
     * adds a Taxi if it does not exist in the list
     * @param taxi
     */
    public void add(Taxi taxi){

        if (taxi != null) {

            if(taxis == null){
                taxis = new ArrayList<>();
                taxis.add(taxi);


            } else {

                if (!containsTaxi(taxi)){
                    taxis.add(taxi);

                }
            }

        }
    }

    /**
     * Returns true if a taxi with the same registration number exists
     * @param taxi
     * @return
     */
    public boolean containsTaxi(Taxi taxi) {

        if(taxis != null && taxi != null) {
            for (Taxi tx : taxis){

                if(taxi.getTaxiRegistrationNumber().equals(tx.getTaxiRegistrationNumber())) {
                    return  true;
                }
            }
        }

        return false;
    }

    public ArrayList<Taxi> getTaxis() {
        return taxis;
    }

    public void setTaxis(ArrayList<Taxi> taxis) {
        this.taxis = taxis;
    }
}
