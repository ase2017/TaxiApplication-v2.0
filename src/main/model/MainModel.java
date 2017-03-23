package main.model;

import java.util.Observable;

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


    public MainModel(int numberOfTaxis, int numberOfGroups, int numberOfWindows, int maxNumberOfPassengersPerGroup) {
        this.taxiData = new TaxiData(numberOfTaxis,numberOfGroups,maxNumberOfPassengersPerGroup);
        windows = new Window[numberOfWindows];
        windowsThreads = new Thread[numberOfWindows];

        for(int i  = 0; i < windows.length; i++) {
            windows[i] = new Window(taxiData,i);
            windowsThreads[i] = new Thread(windows[i]);
            windowsThreads[i].setName("Window " + i);
        }

        stats = new Stats(taxiData,windows);


    }


    /* ******************************************************************** */
    /* ************************ GUI METHODS ******************************* */
    /* ******************************************************************** */


    /**
     * For individual windows' "End of day"  button
     * Simulates the end of day of the window of the given index
     * @param index the index / number of the window
     */
    public void endOfDay(int index){
        windows[index].setStopped();
    }

    /**
     * For STOP button (general button)
     * empties the queues and simulates the end of the day (all windows leave)
     */
    public void stopAllWindows(){

        for(int i  = 0; i < windows.length; i++) {
            windows[i].setStopped();
        }
    }


    /**
     * For START button (general button)
     * starts the threads
     */
    public void run(){

        for(int i  = 0; i < windows.length; i++) {
            windowsThreads[i].start();
        }
    }

    /* ******************************************************************** */
    /* ******************************************************************** */
    /* ******************************************************************** */

    /* ****************** GETTERS / SETTERS ********************************* */


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
