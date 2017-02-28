package main.exceptions;

/**
 * Class Name: InvalidDistanceException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid distance.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


@SuppressWarnings("serial")
public class InvalidDistanceException extends Exception{

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid distance was found
	 * @param line The line that the invalid distance was found
	 */
	public InvalidDistanceException(String filename, int line){
		
		super("Error! The distance is not in the correct range (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
}
