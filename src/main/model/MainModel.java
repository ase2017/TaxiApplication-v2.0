package main.model;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainModel {

    private TaxiData taxiData;
    private Window[] windows;
    private Thread[] threads;

    Random random = new Random();

    public MainModel(int numberOfTaxis, int numberOfGroups, int numberOfWindows) {
        this.taxiData = new TaxiData(numberOfTaxis,numberOfGroups);

        windows = new Window[numberOfWindows];
        threads = new Thread[numberOfWindows];

        for(int i  = 0; i < numberOfWindows; i++) {
            windows[i] = new Window(taxiData,i);
            threads[i] = new Thread(windows[i]);
            threads[i].setName("Window " + i);
        }
    }

    public void run(){

        for(int i  = 0; i < windows.length; i++) {
            threads[i].start();
            try{
                TimeUnit.SECONDS.sleep(2);
                //1-2 seconds “sleep” (of waiting)
            } catch (InterruptedException e){

            }

        }

        /*for(int i  = 0; i < 5; i++) {
            System.out.println(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            taxiData.addGroup();
            System.out.println(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size());
            System.out.println("ADDED A GROUP");
            try{
                TimeUnit.SECONDS.sleep(5);
                //1-2 seconds “sleep” (of waiting)
            } catch (InterruptedException e){

            }

        }*/

        // TODO : NOT REMOVE
        /*while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getTaxiQueue().getTaxisQueue().size() > 0 ) {
            randomlyPutWindowsToBreak();

            try{
                TimeUnit.SECONDS.sleep(1);
                //1-2 seconds “sleep” (of waiting)
            } catch (InterruptedException e){

            }
        }*/
        System.currentTimeMillis();
    }



    /**
     *
     * Randomly puts a window to "BREAK" status
     */
    public void randomlyPutWindowsToBreak(){
        for(int i = 0; i < threads.length; i++){
            if(windows[i].getStatus().equals(WindowStatuses.AVAILABLE.toString())){
                try{

                    threads[i].sleep(5000);
                    windows[i].setStatus(WindowStatuses.BREAK.toString());
                    System.out.println("@@@@@@@@@@@PUT window " + i + " to break");
                } catch (InterruptedException e){

                }
            }
        }
    }

}
