package main.model;


import java.util.Observable;

public class Window extends Observable implements Runnable{

    private GroupOfPassengers groupOfPassengers;
    private Taxi taxi;
    private String status;
    private TaxiData taxiData;

    @Override
    public void run() {


        if(status.equals("AVAILABLE")) {
            pickGroup();
            allocateTaxi();
        }

    }

    public void pickGroup() {
        //TODO : setGroupOfPassengers(popGroup()); //SYNC
        setStatus(Status.BUSY.getString());
        //1-2 seconds “sleep” (of waiting)

    }

    public void allocateTaxi(){
        //TODO : setTaxi(popTaxi());
        //2-3 seconds “sleep” (of waiting)
        setTaxi(null);
        setGroupOfPassengers(null);
        // timer
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

    public Window() {
        groupOfPassengers = null;
        taxi = null;
        this.status = Status.AVAILABLE.getString();
        taxiData = null;
    }

    public Window(GroupOfPassengers groupOfPassengers, Taxi taxi, String status, TaxiData taxiData) {
        this.groupOfPassengers = groupOfPassengers;
        this.taxi = taxi;
        this.status = status;
        this.taxiData = taxiData;

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
