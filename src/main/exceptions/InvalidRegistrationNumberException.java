package main.exceptions;

/**
 * Class Name: InvalidRegistrationNumberException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid registration number.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


@SuppressWarnings("serial")
public class InvalidRegistrationNumberException extends Exception{

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid registration number was found
	 * @param line The line that the invalid registration number was found
	 */
	public InvalidRegistrationNumberException(String filename, int line){
		
		super("Error! Wrong registration number (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
	
}
