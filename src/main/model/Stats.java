package main.model;

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
    public double getAverageNumberOfPassengersPerWindow(){

        double passPerWindow = 0;

        if(windows != null && windows.length > 0 ){
            for(Window window : windows) {
                if(window != null){
                    passPerWindow += window.getTotalNumberOfPassengersServed();
                }
            }

            passPerWindow /= windows.length;

        }

        return passPerWindow;
    }



    public double getAverageTaxisPerWindow() {

        double taxisPerWindow = 0;

        if(windows != null && windows.length > 0 ){

            for(int i=0; i<windows.length; i++) {
                if(windows[i] != null){
                    taxisPerWindow += getNumberOfTaxisThisWindow(i);
                }
            }

            taxisPerWindow /= windows.length;

        }

        return taxisPerWindow;
    }


    public double getAverageGroupsPerWindow() {

        double groupsPerWindow = 0;

        if(windows != null && windows.length > 0 ){

            for(int i=0; i<windows.length; i++) {
                if(windows[i] != null){
                    groupsPerWindow += getNumberOfGroupsThisWindow(i);
                }
            }

            groupsPerWindow /= windows.length;

        }

        return groupsPerWindow;
    }



    public double getAverageWorkingTimePerWindow() {

        double avgTime = 0;

        if(windows != null && windows.length > 0 ){

            for(int i=0; i<windows.length; i++) {
                if(windows[i] != null){
                    avgTime += getWorkingTimeThisWindow(i);
                }
            }

            avgTime /= windows.length;

        }

        return avgTime;

    }


    public int getNumberOfPassengerThisWindow( int windowIndex ) {


        if( windows != null && windowIndex < windows.length )
            return windows[windowIndex].getTotalNumberOfPassengersServed();
        else
            return -1;

    }


    public int getNumberOfTaxisThisWindow( int windowIndex ) {


        if ( windows != null && windowIndex < windows.length )
            return windows[windowIndex].getTotalNumberOfAllocatedTaxis();
        else
            return -1;

    }


    public int getNumberOfGroupsThisWindow( int windowIndex ) {


        if ( windows != null && windowIndex < windows.length )
            return windows[windowIndex].getTotalNumberOfAllocatedTaxis();
        else
            return -1;

    }



    public double getWorkingTimeThisWindow( int windowIndex ) {


        if ( windows != null && windowIndex < windows.length )
            return (double)( windows[windowIndex].getWorkingEndTime() - windows[windowIndex].getWorkingStartTime() ) / 1000;
        else
            return -1;
    }



    public void printIndividualWindowStats() {

        for(int i=0; i<windows.length; i++) {

            System.out.println( "Window " + i + ": " +
                    " Working Time: " + getWorkingTimeThisWindow(i) + " NumOfGroups: " + getNumberOfGroupsThisWindow(i) +
                     " NumOfTaxis: " + getNumberOfTaxisThisWindow(i) + " NumOfPassengers: " + getNumberOfPassengerThisWindow(i) );
        }

    }

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
