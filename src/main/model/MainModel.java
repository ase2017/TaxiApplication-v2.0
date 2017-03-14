package main.model;

import java.util.concurrent.TimeUnit;

public class MainModel {

    private TaxiData taxiData;
    private Window[] windows;
    private Thread[] threads;

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
            //System.out.println("State of thread " + i + " " + threads[i].getState());
            threads[i].start();
            try{
                TimeUnit.SECONDS.sleep(2);
                //1-2 seconds “sleep” (of waiting)
            } catch (InterruptedException e){

            }
            //System.out.println("State of thread " + i + " " + threads[i].getState());
        }
/*
        int i = 0;
        while(taxiData.getTaxiQueue().getTaxisQueue().size() > 0
                && taxiData.getTaxiQueue().getTaxisQueue().size() > 0) {
            System.out.println("Starting! Lists are not empty");


            while(i < windows.length && !windows[i].getStatus().equals("AVAILABLE")) {
                i++;
            }
            if(windows[i].getStatus().equals("AVAILABLE")) {
                System.out.println(threads[i].getName() + " is running");
                System.out.println("TEST2 : State of thread " + i + " " + threads[i].getState());
                windows[i].();
                System.out.println("TEST2 : State of thread " + i + " " + threads[i].getState());
            }
            //i = 0;

        }
    */
    }

}
