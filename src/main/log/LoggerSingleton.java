package main.log;

/**
 * Created by Giorgos on 21-Mar-17.
 */
public class LoggerSingleton {

    private static LoggerSingleton instance = null;
    private String loggerData = "";

    private LoggerSingleton(){ }

    public static LoggerSingleton getInstance(){

        if(instance == null){
            instance = new LoggerSingleton();
        }

        return instance;

    }

    public void addRecord(int windowID, String destinationName, int passengerNumber,
                          int remainingPassengers, String taxiRegistrationNumber){

        loggerData += String.format("%s%s%-5s%-13s%-40s%-12s%-6s%-11s%-7s%-6s%-10s", "W", windowID, ":", "Destination:", destinationName,
                "Passengers:", passengerNumber, "Remaining:", remainingPassengers, "Taxi:", taxiRegistrationNumber);

    }

    public void appendData(){
        DataFileWriter d = new DataFileWriter();
        d.exportLogs(loggerData);
    }

}
