package main.log;

/**
 * Class Name: LoggerSingleton.java
 *
 * Description: This class follows the Singleton (Lazy Initialization) Pattern.
 *              It is used by the methods to insert records into the string
 *              which is going to be printed in the output file.
 *
 * @author George Goniotakis
 * @since Mar 18, 2017
 */
public class LoggerSingleton {

    private static LoggerSingleton instance = null; //Instance of LoggerSingleton
    private String loggerData = ""; //String that contains the output which is going to be exported

    /**
     * Deactivate the constructor to implement Singleton pattern
     */
    private LoggerSingleton(){ }

    /**
     * We allow only one Object of this class to be created. If this is created
     * return the Object, otherwise create it and return it.
     *
     * @return The LoggerSingleton Object
     */
    public static LoggerSingleton getInstance(){

        if(instance == null){
            instance = new LoggerSingleton();
        }

        return instance;

    }

    /**
     * Add a new record in the output string.
     *
     * @param windowID The window's ID
     * @param destinationName The destination's name
     * @param passengerNumber The number of the passengers
     * @param remainingPassengers The remaining number of passengers
     * @param taxiRegistrationNumber The taxi's registration number
     */
    public void addRecord(int windowID, String destinationName, int passengerNumber,
                          int remainingPassengers, String taxiRegistrationNumber){

        loggerData += String.format("%s%s%-5s%-13s%-40s%-12s%-6s%-11s%-7s%-6s%-10s\n", "W", windowID, ":", "Destination:", destinationName,
                "Passengers:", passengerNumber, "Remaining:", remainingPassengers, "Taxi:", taxiRegistrationNumber);

    }

    /**
     * This method is used to add a record that does not follow the format of the strings
     * used in {@link #addRecord(int, String, int, int, String)} method, to the result string.
     *
     * @param string The input string which is going to be used as a record.
     */
    public void add(String string){
        loggerData += string + "\n";
    }

    /**
     * This method creates a new {@link DataFileWriter} Object to print the result string
     * to the text file.
     */
    public void exportData(){
        DataFileWriter d = new DataFileWriter();
        d.exportLogs(loggerData);
    }

}
