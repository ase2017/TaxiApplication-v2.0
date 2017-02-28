package main.exceptions;

/**
 * Class Name: InvalidNumberOfPassengersException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid number of passengers.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

@SuppressWarnings("serial")
public class InvalidNumberOfPassengersException extends Exception{

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid number of passengers was found
	 * @param line The line that the invalid number of passengers was found
	 */
	public InvalidNumberOfPassengersException(String filename, int line){
		
		super("Error! The number of passengers is not correct (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
	
}
