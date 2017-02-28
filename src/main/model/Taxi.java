package main.model;


public class Taxi {

    private String taxiRegistrationNumber;
    private int maximumNumberOfPassengers;

    public Taxi(String taxiRegistrationNumber, int maximumNumberOfPassengers) {
        this.taxiRegistrationNumber = taxiRegistrationNumber;
        this.maximumNumberOfPassengers = maximumNumberOfPassengers;
    }

    public String getTaxiRegistrationNumber() {
        return taxiRegistrationNumber;
    }

    public void setTaxiRegistrationNumber(String taxiRegistrationNumber) {
        this.taxiRegistrationNumber = taxiRegistrationNumber;
    }

    public int getMaximumNumberOfPassengers() {
        return maximumNumberOfPassengers;
    }

    public void setMaximumNumberOfPassengers(int maximumNumberOfPassengers) {
        this.maximumNumberOfPassengers = maximumNumberOfPassengers;
    }
}
