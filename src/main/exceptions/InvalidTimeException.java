package main.exceptions;

/**
 * Class Name: InvalidTimeException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid time.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

@SuppressWarnings("serial")
public class InvalidTimeException extends Exception{

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid time was found
	 * @param line The line that the invalid time was found
	 */
	public InvalidTimeException(String filename, int line){
		
		super("Error! The time of the journey is not in the correct format "
				+ "(in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
	
}
