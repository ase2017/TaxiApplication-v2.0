package main.model;

import java.util.ArrayList;

/**
 * Holds all the taxis already created
 * @author George C. and Jules
 */
public class TaxiList {

    ArrayList<Taxi> taxis = new ArrayList<>();

    public void add(Taxi taxi){
        if (taxi != null) {
            taxi.setArrivalTime(System.currentTimeMillis());
            taxis.add(taxi);
        }

    }

    /**
     * Returns true if a taxi with the same registration number exists
     * @param taxi
     * @return
     */
    public boolean containsTaxi(Taxi taxi) {

        if(taxi != null) {
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
