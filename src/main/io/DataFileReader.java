package main.io;

import main.exceptions.*;
import main.items.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * DataFileReader is a public class that reads the data from four files
 * taxis.txt, journeys.txt, destinations_2016.txt and destinations_2017.txt,
 * then checks if the file is in the wright format, checks if those values exist (not empty),
 * make some converts and finally store them to the appropriate data structure.
 *
 * @author Chiotis
 */
public class DataFileReader {

	public static final String DATA_SEPARATOR = ",";
	public static final String FILE_NAME_FOLDER = "inputFiles/";
	public static final String FILE_NAME_JOURNEYS = "journeys.txt";
	public static final String FILE_NAME_TAXIS = "taxis.txt";
	public static final String FILE_NAME_DESTINATIONS_2016 = "destinations_2016.txt";
	public static final String FILE_NAME_DESTINATIONS_2017 = "destinations_2017.txt";
	public static int line_counter = 0;

	public static boolean objectChecker = true; //Global boolean variable to check if the constructor
												// tried to create an object with wrong parameters.


	/**
	 *
	 * @return a TreeMap of ArrayLists of Journeys.
	 */
	public JourneyTreeMap loadJourney() {

		return journeyChecker( FILE_NAME_FOLDER, FILE_NAME_JOURNEYS );
	}


	/**
	 *
	 * @return a TreeMap of Taxis.
	 */
	public TaxiTreeMap loadTaxis() {

		return taxiChecker( FILE_NAME_FOLDER, FILE_NAME_TAXIS );
	}


	/**
	 *
	 * @return a TreeSet of last year's destinations.
	 */
	public DestinationTreeSet loadDestinations2016() {

		return destination2016Checker( FILE_NAME_FOLDER, FILE_NAME_DESTINATIONS_2016 );

	}


	/**
	 *
	 * @return a TreeMap of current year destinations.
	 */
	public DestinationTreeMap loadDestinations2017() {

		return destination2017Checker( FILE_NAME_FOLDER, FILE_NAME_DESTINATIONS_2017 );
	}

    /**
     * This method is processing a line of the file journeys.txt
     *
     * @param line The line to be processed
     * @return boolean Whether the line does not include any errors or not
     * @throws InvalidInputArgumentsException     If the number of arguments in record is not correct
     * @throws InvalidIDException                 If the journey's ID is null, empty or zero
     * @throws InvalidMaximumVelocityException    If the maximum velocity is null, empty or zero
     * @throws InvalidRegistrationNumberException If the registration number is null, empty or zero
     * @throws InvalidTimeException               If the journey's time is null, empty or zero
     * @throws InvalidNumberOfPassengersException If the number of passengers is null, empty or zero
     * @throws NumberFormatException              If instead of an integer we get a string
     * @throws ArrayIndexOutOfBoundsException     If the reading process fails
     * @throws NullPointerException               If cannot read the file
     */
    public boolean processJourneyLine(String line) {

        String[] journey_info = null;

        try {

            journey_info = line.split(DATA_SEPARATOR, -1);        // split the line using the given separator

            try {

                if (journey_info.length != 5) // check if this line has exactly five words
                    throw new InvalidInputArgumentsException(FILE_NAME_JOURNEYS, line_counter);

                // Then check if any of those five words are an empty string
                // and if this happens  for any of those strings, throw the appropriate exception

                if (journey_info[0] == null || journey_info[0].trim().isEmpty())
                    throw new InvalidIDException(FILE_NAME_JOURNEYS, line_counter);

                if (journey_info[1] == null || journey_info[1].trim().isEmpty())
                    throw new InvalidRegistrationNumberException(FILE_NAME_JOURNEYS, line_counter);

                if (journey_info[2] == null || journey_info[2].trim().isEmpty())
                    throw new InvalidNumberOfPassengersException(FILE_NAME_JOURNEYS, line_counter);

                if (journey_info[3] == null || journey_info[3].trim().isEmpty())
                    throw new InvalidTimeException(FILE_NAME_JOURNEYS, line_counter);

                if (journey_info[4] == null || journey_info[4].trim().isEmpty())
                    throw new InvalidMaximumVelocityException(FILE_NAME_JOURNEYS, line_counter);

                int id = Integer.parseInt(journey_info[0]);  // Converts Journey's id to integer.
                int numOfPassengers = Integer.parseInt(journey_info[2]); // Converts Number of Passenger of this journey to integer.
                double time = Double.parseDouble(journey_info[3]); // Converts the time needed for this journey to double.
                double maxVelocity = Double.parseDouble(journey_info[4]);  // Converts maximum velocity of this journey to double.


            } catch (InvalidInputArgumentsException | InvalidIDException | InvalidMaximumVelocityException
                    | InvalidRegistrationNumberException | InvalidTimeException | InvalidNumberOfPassengersException e) {

                System.out.println(e.getMessage());
                return false;

            } catch (NumberFormatException e) {

                System.out.println("\t --Number Format exception in file: " + FILE_NAME_JOURNEYS + " in line " + line_counter +
                        "[ " + e.getMessage() + " ].");
                return false;

            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("\t --Reading process in file " + FILE_NAME_JOURNEYS + " failed... [ " + e.getMessage() + " ].");
                return false;

            }

        } catch (NullPointerException e) {

            System.out.println("\t --File: " + FILE_NAME_JOURNEYS + " failed to open. [ " + e.getMessage() + " ].");
            return false;
        }

        return true;
    }


    /**
     * journeyChecker reads the file that is specified by the two arguments (default case:
     * journey.txt in inputFiles directory).
     * Checks its structure as to the number of words separated by DATA_SEPARATOR (instance variable),
     * also checks if those words are not empty, converts strings to double or integers when needed,
     * and finally uses Journey's constructor for each valid line and add this journey object to a TreeMap.
     *
     * @param directory: locate the directory's path for the input file.
     * @param filename:  the filename of journey's data.
     * @return an object of JourneyTreeMap, which is a TreeMap of all journeys.
     * @throws NumberFormatException          If instead of an integer we get a string
     * @throws ArrayIndexOutOfBoundsException If the reading process fails
     * @throws IOException                    If there is not input file
     * @throws NullPointerException           If cannot read the file
     */
    public JourneyTreeMap journeyChecker(String directory, String filename) {

        TreeMap<String, ArrayList<Journey>> temporaryTreeMap = new TreeMap<>();
        JourneyTreeMap journeyTreeMap = new JourneyTreeMap(temporaryTreeMap);

        FileReader fd_journeys = null;
        objectChecker = true;

        int id = 0;
        int numOfPassengers = 0;
        double time = 0.0;
        double maxVelocity = 0.0;

        try {

            // Open the file that contains the journeys.
            fd_journeys = new FileReader(directory + filename);
            BufferedReader journey_reader = new BufferedReader(fd_journeys);

            String line = null;
            String[] journey_info = null;
            line_counter = 0;  // Initializes the line counter.

            // Read it line-by-line
            while ((line = journey_reader.readLine()) != null) {

                objectChecker = true;
                line_counter++; // specify the line of the file
                journey_info = line.split(DATA_SEPARATOR, -1); // split the line using the given separator

                try {

                    if (processJourneyLine(line)) {

                        time = Double.parseDouble(journey_info[3]); // Converts the time needed for this journey to double.
                        numOfPassengers = Integer.parseInt(journey_info[2]); // Converts Number of Passenger of this journey to integer.
                        id = Integer.parseInt(journey_info[0]);  // Converts Journey's id to integer.


                        maxVelocity = Double.parseDouble(journey_info[4]);  // Converts maximum velocity of this journey to double.


                        // Creates a Journey Object
                        Journey jrn = new Journey(id, journey_info[1], numOfPassengers, time, maxVelocity);


                        if (objectChecker) { // if the object has been created normally

                            journeyTreeMap.addJourney(jrn);   // add this Journey to the JourneyTreeMap
                            // -- which is a TreeMap of ArrayLists of Journey's objects --
                            // using the addJourney public method of the
                            // JourneyTreeMap class.
                        }
                    }

                } catch (NumberFormatException e) {

                    System.out.println("\t --Number Format exception in file: " + filename + " in line " + line_counter +
                            "[ " + e.getMessage() + " ].");

                } catch (ArrayIndexOutOfBoundsException e) {

                    System.out.println("\t --Reading process in file " + filename + " failed... [ " + e.getMessage() + " ].");

                }

            }

        } catch (IOException | NullPointerException e) {

            System.out.println("\t --File: " + filename + " failed to open. [ " + e.getMessage() + " ].");
        } finally {

            try {
                if (fd_journeys != null)
                    fd_journeys.close(); // Close the file reader.

            } catch (IOException e) {

                System.out.println("\t --File: " + filename + " failed to close. [ " + e.getMessage() + " ].");

            }
        }


        return journeyTreeMap;
    }

    /**
     * This method is processing a line of the file destinations_2016.txt
     *
     * @param line The line to be processed
     * @return boolean Whether the line does not include any errors or not
     * @throws InvalidDestinationNameException If the destination's name is null, empty or zero
     */
    public boolean processDestination2016Line(String line) {

        try {

            if (line == null || line.trim().isEmpty())
                throw new InvalidDestinationNameException(FILE_NAME_DESTINATIONS_2016, line_counter);

        } catch (InvalidDestinationNameException e) {

            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }


    /**
     * destination2016Checker reads the file that is specified by the two arguments (default case:
     * destinations_2016.txt in inputFiles directory).
     * <p>
     * Checks its structure as to the number of words separated by DATA_SEPARATOR (instance variable),
     * then checks if those words are not empty,  and for each valid line add this Destination object to a DestinationTreeSet.
     *
     * @param directory: locate the directory's path for the input file.
     * @param filename:  the filename of journey's data.
     * @return an object of DestinationTreeSet, which is a TreeSet of all the destinations visited by the taxis in 2016.
     * @throws IOException                     If the file does not exist
     * @throws NullPointerException            If cannot read file
     */
    public DestinationTreeSet destination2016Checker(String directory, String filename) {

        TreeSet<Destination> temporaryTreeSet = new TreeSet<Destination>();
        DestinationTreeSet destinations2016 = new DestinationTreeSet(temporaryTreeSet);


        FileReader fd_destination_2016 = null;
        objectChecker = true;

        try {

            // Open the file that contains the last's year destinations.
            fd_destination_2016 = new FileReader(directory + filename);
            BufferedReader destination_2016_reader = new BufferedReader(fd_destination_2016);

            String line = null;
            line_counter = 0; // Initializes the line counter

            // Read it line-by-line
            while ((line = destination_2016_reader.readLine()) != null) {

                line_counter++; // Specifies the number of the current line.
                objectChecker = true;


                if (processDestination2016Line(line)) {

                    // Create the Destination Object using the appropriate constructor.
                    Destination dest2016 = new Destination(line);


                    if (objectChecker) { // if the object has been created normally

                        temporaryTreeSet.add(dest2016);        // add this Destination to the DestinationTreeSet
                        // -- which is a TreeSet of Destination's objects --
                        // using the add method of the TreeSet data structure.

                    }
                }
            }

        } catch (IOException | NullPointerException e) {

            System.out.println("\t --File: " + filename + " failed to open. [ " + e.getMessage() + " ].");

        } finally {

            try {
                if (fd_destination_2016 != null)
                    fd_destination_2016.close();    // Close the file descriptor.

            } catch (IOException e) {

                System.out.println("\t --File: " + filename + " failed to close. [ " + e.getMessage() + " ].");

            }
        }

        return destinations2016;
    }

    /**
     * This method is processing a line of the file destinations_2017.txt
     *
     * @param line The line to be processed
     * @return boolean Whether the line does not include any errors or not
     * @throws DuplicateIDException            If a destination with the same ID already exists
     * @throws InvalidIDException              If a destination with null, empty or zero ID exists
     * @throws InvalidInputArgumentsException  If the arguments of a record are null, empty or zero
     * @throws InvalidDestinationNameException If the destination's name is null, empty or zero
     * @throws InvalidDistanceException        If the destination's distance is null, empty or zero
     * @throws InvalidUrbanException           If the urban identifier is null, empty or zero
     * @throws ArrayIndexOutOfBoundsException  If the reading process fails
     * @throws NullPointerException            If cannot open the file
     * @throws NumberFormatException           If instead of a number we get a string
     */
    public boolean processDestination2017Line(String line) {

        String[] destination_2017_info = null;

        try {

            destination_2017_info = line.split(DATA_SEPARATOR, -1);        // Split its words as to the given separator.

            try {

                if (destination_2017_info.length != 4) // check if this line has exactly four words
                    throw new InvalidInputArgumentsException(FILE_NAME_DESTINATIONS_2017, line_counter);

                if (destination_2017_info[0] == null || destination_2017_info[0].trim().isEmpty())
                    throw new InvalidIDException(FILE_NAME_DESTINATIONS_2017, line_counter);

                if (destination_2017_info[1] == null || destination_2017_info[1].trim().isEmpty())
                    throw new InvalidDestinationNameException(FILE_NAME_DESTINATIONS_2017, line_counter);

                if (destination_2017_info[2] == null || destination_2017_info[2].trim().isEmpty())
                    throw new InvalidDistanceException(FILE_NAME_DESTINATIONS_2017, line_counter);

                if (destination_2017_info[3] == null || destination_2017_info[3].trim().isEmpty() ||
                        (!destination_2017_info[3].equals("N") && !destination_2017_info[3].equals("Y")))
                    throw new InvalidUrbanException(FILE_NAME_DESTINATIONS_2017, line_counter);

                int id = Integer.parseInt(destination_2017_info[0]);
                double distance = Double.parseDouble(destination_2017_info[2]);

            } catch (InvalidIDException | InvalidInputArgumentsException | InvalidDestinationNameException |
                    InvalidDistanceException | InvalidUrbanException e) {

                System.out.println(e.getMessage());
                return false;

            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("\t --Reading process in file" + FILE_NAME_DESTINATIONS_2017
                        + " failed... [ " + e.getMessage() + " ].");
                return false;

            } catch (NumberFormatException e) {

                System.out.println("\t --Number Format exception in file: " + FILE_NAME_DESTINATIONS_2017  + " in line " + line_counter +
                        "[ " + e.getMessage() + " ].");
                return false;

            }

        } catch (NullPointerException e) {

            System.out.println("\t --File: " + FILE_NAME_DESTINATIONS_2017 + " failed to open. [ " + e.getMessage() + " ].");
            return false;

        }

        return true;
    }


    /**
     * destination2017Checker reads the file that is specified by the two arguments (default case:
     * destinations_2017.txt in inputFiles directory).
     * <p>
     * Checks its structure as to the number of words separated by DATA_SEPARATOR (instance variable),
     * then checks if those words are not empty, converts strings to double or integers when needed,
     * and for each valid line add this Destination object to a DestinationTreeMap.
     *
     * @param directory: locate the directory's path for the input file.
     * @param filename:  the filename of journey's data.
     * @return an object of DestinationTreeMap, which is a TreeMap of all the destinations visited by the taxis in 2017.
     * @throws NumberFormatException           If instead of a number we get a string
     * @throws ArrayIndexOutOfBoundsException  If the reading process fails
     * @throws IOException                     If the input file does not exist
     * @throws NullPointerException            If cannot open the file
     */
    public DestinationTreeMap destination2017Checker(String directory, String filename) {

        TreeMap<Integer, Destination> temporaryTreeMap = new TreeMap<>();
        DestinationTreeMap destination2017_TreeMap = new DestinationTreeMap(temporaryTreeMap);


        FileReader fd_destination_2017 = null;
        objectChecker = true;

        int id = 0;
        double distance = 0.0;
        boolean urban = false;

        try {

            // Open the file that contains the current's year destinations.
            fd_destination_2017 = new FileReader(directory + filename);
            BufferedReader destination_2017_reader = new BufferedReader(fd_destination_2017);

            String line = null;
            String[] destination_2017_info = null;
            line_counter = 0;        // Initializes the line counter.

            // Read it line-by-line
            while ((line = destination_2017_reader.readLine()) != null) {

                line_counter++;        // Specify the current number of the line.
                objectChecker = true;

                try {

                    if (processDestination2017Line(line)) {

                        destination_2017_info = line.split(DATA_SEPARATOR, -1);  // Split its words as to the given separator.
                        id = Integer.parseInt(destination_2017_info[0]);  // Converts the destination's ID to integer.
                        distance = Double.parseDouble(destination_2017_info[2]);  // Converts the distance of its journey to double.

                        if (destination_2017_info[3].equals("Y")) // If the Urban Variable is Y(es) return true.
                            urban = true;
                        else if (destination_2017_info[3].equals("N")) // If the Urban Variable is N(o) return false.
                            urban = false;


                        // Create the Destination Object using the appropriate constructor.
                        Destination dest2017 = new Destination(id, destination_2017_info[1], distance, urban);

                        if (objectChecker) { // if the object has been created normally

                            try {

                                destination2017_TreeMap.addDestination2017(dest2017);    // add this Destination to the DestinationTreeMap
                                // -- which is a TreeMap of Destination's objects --
                                // using the public addDestination2017 method of the
                                // DestinationTreeMap's class.

                            } catch (DuplicateIDException e) {

                                System.out.println(e.getMessage());

                            }
                        }
                    }

                } catch (NumberFormatException e) {

                    System.out.println("\t --Not a number exception in file: " + FILE_NAME_DESTINATIONS_2017
                            + " in line " + line_counter + "[ " + e.getMessage() + " ].");

                }
            }
        } catch (IOException | NullPointerException e) {

            System.out.println("\t --File: " + filename + " failed to open. [ " + e.getMessage() + " ].");

        } finally {

            try {
                if (fd_destination_2017 != null)
                    fd_destination_2017.close();    // Close the file descriptor.

            } catch (IOException e) {

                System.out.println("\t --File: " + filename + " failed to close. [ " + e.getMessage() + " ].");

            }
        }

        return destination2017_TreeMap;
    }


    /**
     * This method is processing a line of the file taxis.txt
     *
     * @param line The line to be processed
     * @return boolean Whether the line does not include any errors or not
     * @throws InvalidRegistrationNumberException If the registration number is null, empty or zero
     * @throws InvalidTaxiNameException           If the driver's name is null, empty or zero
     * @throws InvalidBrandNameException          If the brand name is null, empty or zero
     * @throws InvalidInputArgumentsException     If the number of arguments is not correct
     * @throws ArrayIndexOutOfBoundsException     If the reading process fails
     * @throws NullPointerException               If cannot read file
     */
    public boolean processTaxiLine(String line) {

        String[] taxi_info = null;
        String[] nameComponents = null;

        try {

            taxi_info = line.split(DATA_SEPARATOR, -1); // split the line using the given separator.

            try {

                nameComponents = taxi_info[1].split(" ");  // slit the driver's name (first name, last name).

                if (taxi_info.length != 3) // check if this line has exactly three words
                    throw new InvalidInputArgumentsException(FILE_NAME_TAXIS, line_counter);

                // Then check if any of those three words are an empty string
                // and if this happens for any of those strings, throw the appropriate exception

                if (taxi_info[0] == null || taxi_info[0].trim().isEmpty())
                    throw new InvalidRegistrationNumberException(FILE_NAME_TAXIS, line_counter);

                if (taxi_info[1] == null || taxi_info[1].trim().isEmpty()
                        || nameComponents.length < 2)
                    throw new InvalidTaxiNameException(FILE_NAME_TAXIS, line_counter);

                if (taxi_info[2] == null || taxi_info[2].trim().isEmpty())
                    throw new InvalidBrandNameException(FILE_NAME_TAXIS, line_counter);

            } catch (ArrayIndexOutOfBoundsException e) {

                System.out.println("\t --Reading process in file " + FILE_NAME_TAXIS + " failed... [ " + line_counter + " ].");
                return false;

            } catch (InvalidRegistrationNumberException | InvalidTaxiNameException | InvalidBrandNameException
                    | InvalidInputArgumentsException e) {

                System.out.println(e.getMessage());
                return false;

            }

        } catch (NullPointerException e) {
            System.out.println("\t --File: " + FILE_NAME_TAXIS + " failed to open. [ " + e.getMessage() + " ].");
            return false;
        }

        return true;
    }


    /**
     * taxiChecker reads the file that is specified by the two arguments (default case:
     * taxi.txt in inputFiles directory).
     * Checks its structure as to the number of words separated by DATA_SEPARATOR (instance variable),
     * also checks if those words are not empty, converts strings to double or integers when needed,
     * and finally uses Taxi's constructor for each valid line and add this Taxi object to a TreeMap.
     *
     * @param directory: locate the directory's path for the input file.
     * @param filename:  the filename of journey's data.
     * @return an object of taxiTreeMap, which is a TreeMap of all taxis.
     * @throws IOException                        If the file does not exist
     * @throws NullPointerException               If cannot read file
     */
    public TaxiTreeMap taxiChecker(String directory, String filename) {

        TreeMap<String, Taxi> temporaryTreeMap = new TreeMap<>();
        TaxiTreeMap taxiTreeMap = new TaxiTreeMap(temporaryTreeMap);

        FileReader fd_taxis = null;
        objectChecker = true;

        try {

            // Open the file that contains the taxis.
            fd_taxis = new FileReader(directory + filename);
            BufferedReader taxis_reader = new BufferedReader(fd_taxis);

            String line = null;
            String[] taxi_info = null;
            line_counter = 0;        // Initializes the line counter.

            // Read it line-by-line
            while ((line = taxis_reader.readLine()) != null) {

                objectChecker = true;
                line_counter++; // specify the line of the file


                if (processTaxiLine(line)) {

                    try {

                        taxi_info = line.split(DATA_SEPARATOR, -1); // split the line using the given separator.

                        // Creates a Taxi Object
                        Taxi tx = new Taxi(taxi_info[0].trim(), taxi_info[1], taxi_info[2].trim());


                        if (objectChecker) {  // if the object has been created normally

                            taxiTreeMap.addTaxi(tx); // add this Taxi to the TaxiTreeMap
                            // -- which is a TreeMap of Taxi's objects --
                            // using the addTaxi public method of the
                            // TaxiTreeMap class.
                        }

                    } catch (NullPointerException e) {

                        System.out.println("\t --File: " + filename + " failed to open. [ " + e.getMessage() + " ].");

                    }

                }
            }
        } catch (IOException | NullPointerException e) {

            System.out.println("\t --File: " + filename + " failed to open. [ " + e.getMessage() + " ].");

        } finally {

            try {

                if (fd_taxis != null)
                    fd_taxis.close();  // Close the file descriptor.

            } catch (IOException e) {

                System.out.println("\t --File: " + filename + " failed to close. [ " + e.getMessage() + " ].");

            }
        }

        return taxiTreeMap;
    }


}
