package main.model;


import java.util.Observable;
import java.util.concurrent.TimeUnit;

public class Window extends Observable implements Runnable{

    private GroupOfPassengers groupOfPassengers;
    private Taxi taxi;
    private String status;
    private TaxiData taxiData;

    @Override
    public void run() {



    }

   public void work(){
       System.out.println("WORKING");
        taxiData.getTaxiQueue().popTaxi();
        pickGroup();
        allocateTaxi();
    }

    public void pickGroup() {
        //TODO : setGroupOfPassengers(popGroup()); //SYNC
        setStatus(Status.BUSY.getString());
        try{
            TimeUnit.SECONDS.sleep(1);
            //1-2 seconds “sleep” (of waiting)
        } catch (InterruptedException e){

        }

        groupOfPassengers = taxiData.getPassengerQueue().popGroup();


    }

    public void allocateTaxi(){
        //TODO : setTaxi(popTaxi());
        try{
            TimeUnit.SECONDS.sleep(3);
            //2-3 seconds “sleep” (of waiting)
        } catch (InterruptedException e){

        }

        setTaxi(null);
        setGroupOfPassengers(null);

        try{
            TimeUnit.SECONDS.sleep(1);
            //2-3 seconds “sleep” (of waiting)
        } catch (InterruptedException e){

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

    public Window() {
        groupOfPassengers = null;
        taxi = null;
        this.status = Status.AVAILABLE.getString();
        taxiData = null;
    }

    public Window(TaxiData taxiData) {
        groupOfPassengers = null;
        taxi = null;
        this.status = Status.AVAILABLE.getString();
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
