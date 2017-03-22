package main.model;

/**
 *
 * Class that generates statistics for several
 * aspects of the application.
 *
 * @author George C. and Jules
 *
 */
public class Stats {

    TaxiData taxiData;
    Window[] windows;


    public Stats(TaxiData taxiData, Window[] windows) {

        this.taxiData = taxiData;
        this.windows = windows;

    }

    /**
     * Get the average number of passengers per window.
     *
     * @return the average of passengers served by all windows.
     */
    public double getAverageNumberOfPassengersPerWindow(){

        double passPerWindow = 0;

        if(windows != null && windows.length > 0 ){     // Check if there are windows.
            for(Window window : windows) {              // For each window
                if(window != null){                     // Accumulate the number of Passenger served.
                    passPerWindow += window.getTotalNumberOfPassengersServed();
                }
            }

            passPerWindow /= windows.length;        // Divide the accumulated result
                                                    // by the number of windows.

        }

        return passPerWindow;
    }


    /**
     * Get the average number of Taxis served by all windows.
     *
     * @return the average number of Taxis per window.
     */
    public double getAverageTaxisPerWindow() {

        double taxisPerWindow = 0;

        if(windows != null && windows.length > 0 ){     // Check if there are windows.

            for(int i=0; i<windows.length; i++) {       // For each window
                if(windows[i] != null){                 // Accumulate the number of Taxis served.
                    taxisPerWindow += getNumberOfTaxisThisWindow(i);
                }
            }

            taxisPerWindow /= windows.length;       // Divide the accumulated result
                                                    // by the number of windows.
        }

        return taxisPerWindow;
    }


    /**
     * Get the average number of Groups served by all windows.
     *
     * @return the average number of Groups per window.
     */
    public double getAverageGroupsPerWindow() {

        double groupsPerWindow = 0;

        if(windows != null && windows.length > 0 ){     // Check if there are windows.

            for(int i=0; i<windows.length; i++) {       // For each window
                if(windows[i] != null){                 // Accumulate the number of Groups served.
                    groupsPerWindow += getNumberOfGroupsThisWindow(i);
                }
            }

            groupsPerWindow /= windows.length;       // Divide the accumulated result
                                                    // by the number of windows.

        }

        return groupsPerWindow;
    }


    /**
     * Get the average Working Time per window.
     *
     * @return the average working time per Window.
     */
    public double getAverageWorkingTimePerWindow() {

        double avgTime = 0;

        if(windows != null && windows.length > 0 ){     // If there are windows

            for(int i=0; i<windows.length; i++) {       // For each window
                if(windows[i] != null){                 // Accumulate the window's working time
                    avgTime += getWorkingTimeThisWindow(i);
                }
            }

            avgTime /= windows.length;      // Divide the accumulated result
                                            // by the number of windows.

        }

        return avgTime;

    }


    /**
     * Get the number of Passengers served by the indexed window.
     *
     * @param windowIndex, the index of window into array of windows.
     * @return the number of Passengers served by the indexed window.
     */
    public int getNumberOfPassengerThisWindow( int windowIndex ) {


        if( windows != null && windowIndex < windows.length ) {  // If there is such window
            // Return the number of Passengers served.
            return windows[windowIndex].getTotalNumberOfPassengersServed();
        }
        else     // return -1 in case that there is no such window.
            return -1;

    }


    /**
     *
     * Get the number of Taxis served by the indexed window.
     *
     * @param windowIndex, the index of window into array of windows.
     * @return the number of Taxis served by the indexed window.
     */
    public int getNumberOfTaxisThisWindow( int windowIndex ) {


        if ( windows != null && windowIndex < windows.length ) {    // If there is such a window

            // Return the number of Taxis served.
            return windows[windowIndex].getTotalNumberOfAllocatedTaxis();
        }
        else    // return -1 in case that there is no such window.
            return -1;

    }


    /**
     *
     * Get the number of Groups served by the indexed window.
     *
     * @param windowIndex, the index of window into array of windows
     * @return the number of Groups served by the
     */
    public int getNumberOfGroupsThisWindow( int windowIndex ) {


        if ( windows != null && windowIndex < windows.length ) {    // If there is such window

            // Return the number of Groups served.
            return windows[windowIndex].getTotalNumberOfGroupsServed();
        }
        else     // return -1 in case that there is no such window.
            return -1;

    }


    /**
     *
     * Get the working Time for the indexed window in seconds.
     *
     * @param windowIndex, the index of window into array of windows
     * @return the working Time for this window in seconds
     */
    public double getWorkingTimeThisWindow( int windowIndex ) {


        if ( windows != null && windowIndex < windows.length ) {    // If there is such window

            // Return the working time
            // End_Time - Start_Time divided by 1000 to get seconds instead of milliseconds.
            return (double) (windows[windowIndex].getWorkingEndTime() - windows[windowIndex].getWorkingStartTime()) / 1000;
        }
        else        // return -1 in case that there is no such window.
            return -1;
    }


    /***********************************************************
     *
     * Getters and Setters
     *
     ***********************************************************/

    public TaxiData getTaxiData() {
        return taxiData;
    }

    public void setTaxiData(TaxiData taxiData) {
        this.taxiData = taxiData;
    }

    public Window[] getWindows() {
        return windows;
    }

    public void setWindows(Window[] windows) {
        this.windows = windows;
    }



}
