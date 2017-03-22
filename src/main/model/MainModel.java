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

    /**
     * For PAUSE / RESUME button (general button)
     */
    /*public void pauseOrResumeAllWindows(){
        for (int i = 0; i < windows.length;i++){
            windows[i].setOnBreak();
            try{
                windowsThreads[i].sleep(10000);
            } catch (InterruptedException e) {

            }

        }

    }*/
   /* public void pauseOrResumeAllWindowsOnBreak(){
        for (int i = 0; i < windows.length;i++){
            if(windows[i].getStatus().equals(WindowStatuses.AVAILABLE.toString())) {
                windows[i].setOnBreak();
            }
        }

    }*/


    public void goBreakOrGoBack(int index){
        System.out.println("test index " + index);
        System.out.println(windows[index].getStatus());
            if(windows[index].getStatus().equals(WindowStatuses.AVAILABLE.toString())) {
                System.out.println("currently available");
                windows[index].setOnBreak(true);

            } else if(windows[index].getStatus().equals(WindowStatuses.BREAK.toString())){
                System.out.println("currently on break");
                windows[index].setOnBreak(false);
            }

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



        /* ********** STILL have to sort out how to make it work for a tick box ************* */
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



    /**
     *
     * Randomly puts a window to "BREAK" status
     */
    public void randomlyPutWindowsToBreak(){
        for(int i = 0; i < windowsThreads.length; i++){
            if(windows[i].getStatus().equals(WindowStatuses.AVAILABLE.toString())){
                try{

                    windowsThreads[i].sleep(5000);
                    windows[i].setStatus(WindowStatuses.BREAK.toString());
                    System.out.println("@@@@@@@@@@@PUT window " + i + " to break");
                } catch (InterruptedException e){

                }
            }
        }
    }

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
