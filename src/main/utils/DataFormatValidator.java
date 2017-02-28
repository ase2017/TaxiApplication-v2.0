package main.utils;

import main.items.CarBrand;

import java.util.regex.Pattern;


/**
 *
 * @author Chiotis
 *
 * DataFormatValidator exclusively contains static instance variables and methods so to be accessible
 * from all the other classes. It validates all the input data from the four data files taxis.txt, journeys.txt,
 * destinations_2016.txt and destinations_2017.txt just before the creation of the objects.
 *
 * The instance variables are static and final, so that cannot be mutable from any methods.
 *
 * Methods are static, so to be accessible from each of the three constructors, (Taxi, Journey and Destination).
 *
 */
public class DataFormatValidator {



	private static final int MIN_DESTINATION_NAME_LENGTH = 3;
	private static final int MAX_DESTINATION_NAME_LENGTH = 30;
	private static final int MIN_DRIVER_NAME_LENGTH = 5;
	private static final int MAX_DRIVER_NAME_LENGTH = 30;
	private static final int MIN_NUMBER_OF_PASSENGERS = 1;
	private static final int MAX_NUMBER_OF_PASSENGERS = 8;
	private static final double MIN_VELOCITY = 0.0;
	private static final double MAX_VELOCITY = 130.0;
	private static final double MIN_DISTANCE = 0.0;
	private static final double MAX_DISTANCE = 200.0;
	private static final double MIN_TIME = 0.0;

	/**
	 * Validates the taxi's registration number, as to its length.
	 *
	 * @param registrationNumber The registration number
	 * @return true if the registration number's length equals the indicated length
	 *         by the corresponding instance variable.
	 *         Else return false.
	 */
	public static boolean validateRegistrationNumber(String registrationNumber) {

		if ( registrationNumber == null || !Pattern.matches("(^[A-Z]{2})([0-9]{3})([A-Z]{2})", registrationNumber))
			return false;
		else
			return true;

	}

	/**
	 * Validates the driver's name, as to the range of its length and also checks
	 * the number of components that driver's name consist.
	 *
	 * @param driverName The driver's name
	 * @return true if the taxi's driver name is in the indicated range
	 *         by the corresponding instance variables.
	 *         Else return false.
	 */
	public static boolean validateDriverName(String driverName) {

		if (driverName == null)
			return false;
		else if (driverName.length() >= MIN_DRIVER_NAME_LENGTH && driverName.length() <= MAX_DRIVER_NAME_LENGTH) {

			String [] nameComponents = driverName.split(" ", -1);		// slit the driver's name (first name, last name).

			if ( nameComponents.length > 1 )
				return true;
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * Validates the taxi's brand name, as to the range of its length. 
	 * 
	 * @param brandName The brand's name
	 * @return true if the brand name is in the Enum CarBrand
	 *         Else return false. 
	 */
	public static boolean validateBrand(String brandName) {

		if(CarBrand.containsBrand(brandName))
			return true;
		else
			return false;
	}

	/**
	 * Validates the journey's destination name, as to the range of its length.
	 *
	 * @param destinationName The destination's name
	 * @return true if the destination's name is in the indicated range
	 *              by the corresponding instance variables.
	 *              Else return false.
	 */
	public static boolean validateDestinationName(String destinationName) {

		if ( destinationName == null )
			return false;
		else if ( destinationName.length() >= MIN_DESTINATION_NAME_LENGTH && destinationName.length() <= MAX_DESTINATION_NAME_LENGTH )
			return true;
		else
			return false;
	}

	/**
	 * Validates the distance covered by the taxi in a journey, as to a specified range.
	 *
	 * @param distance The journey's distance
	 * @return true if the distance covered by the taxi is in the indicated range
	 *              by the corresponding instance variables.
	 *              Else return false.
	 */
	public static boolean validateDistance(double distance) {

		if (distance > MIN_DISTANCE && distance <= MAX_DISTANCE)
			return true;
		else
			return false;
	}

	/**
	 * Validates the number of the passenger, as to a specified range.
	 *
	 * @param passengerNumber The number of passengers
	 * @return true if the number of the passengers are in the indicated range by the
	 *         corresponding instance variables.
	 */
	public static boolean validatePassengerNumber(int passengerNumber) {

		if (passengerNumber >= MIN_NUMBER_OF_PASSENGERS && passengerNumber <= MAX_NUMBER_OF_PASSENGERS)
			return true;
		else
			return false;
	}

	/**
	 * Validates the time needed a journey, as to its sign.
	 *
	 * @param time The journey's total duration
	 * @return true if time is positive.
	 *         Else return false.
	 */
	public static boolean validateTime(double time) {

		if (time > MIN_TIME)
			return true;
		else
			return false;
	}

	/**
	 * Validates the taxi's maximum velocity of a journey.
	 *
	 * @param maxVelocity The journey's maximum velocity
	 * @return true if the maximum velocity is in the indicated range
	 *         by the corresponding instance variables.
	 */
	public static boolean validateMaximumVelocity(double maxVelocity) {

		if (maxVelocity >= MIN_VELOCITY && maxVelocity <= MAX_VELOCITY)
			return true;
		else
			return false;
	}

	/**
	 * Validates whether the destination is in an urban area or not.
	 *
	 * @param urban The urban identifier
	 * @return true if the input is "Y" or "N"
	 *              else return false.
	 */
	public static boolean validateUrban(String urban) {

		if ( urban == null )
			return false;
		else if (urban.equals("Y") || urban.equals("N"))
			return true;
		else
			return false;
	}

	/**
	 * Validates the registration ID, as to its sign.
	 *
	 * @param destinationID The destination's ID
	 * @return true if it is positive
	 *         else return false.
	 */
	public static boolean validateDestinationID(int destinationID){

		if(destinationID > 0) {
			return true;
		}

		return false;
	}
}