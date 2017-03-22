package main.model;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Model class that contains all other model info
 * @author Jules and George C.
 */
public class MainModel extends Observable{

    private TaxiData taxiData;
    private Window[] windows;
    private Thread[] windowsThreads;
    private Stats stats;

    public MainModel(){

    }

    public MainModel(int numberOfTaxis, int numberOfGroups, int numberOfWindows) {
        this.taxiData = new TaxiData(numberOfTaxis,numberOfGroups);
        windows = new Window[numberOfWindows];
        windowsThreads = new Thread[numberOfWindows];

        for(int i  = 0; i < windows.length; i++) {
            windows[i] = new Window(taxiData,i);
            windowsThreads[i] = new Thread(windows[i]);
            windowsThreads[i].setName("Window " + i);
        }

        stats = new Stats(taxiData,windows);


    }


    /* ************* GUI methods ********************* */



    public void endOfDay(int index){

        windows[index].setStopped();


    }

    /**
     * For STOP button (general button)
     */
    public void stopAllWindows(){
        taxiData.getPassengerQueue().getGroupOfPassengersQueue().clear();
        taxiData.getTaxiQueue().getTaxisQueue().clear();
        for(int i  = 0; i < windows.length; i++) {
            windows[i].setStopped();
        }
    }


    /**
     * For START button (general button)
     */
    public void run(){



        for(int i  = 0; i < windows.length; i++) {
            windowsThreads[i].start();
            try{
                TimeUnit.SECONDS.sleep(1);
                //1-2 seconds “sleep” (of waiting)
            } catch (InterruptedException e){

            }

        }




    }

    /**
     * For PAUSE button (general button)
     */
    /*public void pauseAllWindows(){
        for (Window window : windows){
            window.setOnBreak();
        }
    }*/



    /* *************************************************** */





    public Window[] getWindows() {
        return windows;
    }

    public void setWindows(Window[] windows) {
        this.windows = windows;
    }

    public TaxiData getTaxiData() {
        return taxiData;
    }

    public void setTaxiData(TaxiData taxiData) {
        this.taxiData = taxiData;
    }

    public Thread[] getWindowsThreads() {
        return windowsThreads;
    }

    public void setWindowsThreads(Thread[] windowsThreads) {
        this.windowsThreads = windowsThreads;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }


}
