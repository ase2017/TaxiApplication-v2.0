package main.model;

import main.Main;

/**
 * Class that generates stats
 */
public class Stats {

    TaxiData taxiData;
    Window[] windows;

    public Stats(TaxiData taxiData, Window[] windows) {
        this.taxiData = taxiData;
        this.windows = windows;
    }

    /**
     * Get the average number of passengers per window
     * @return
     */
    public int getAverageNumberOfPassengersPerWindow(){

        int res = 0;

        if(windows != null && windows.length > 0 ){
            for(Window window : windows) {
                if(window != null){
                    res = window.getTotalNumberOfPassengersServed();
                }
            }

            res /= windows.length;

        }

        return res;
    }


}
