package main.exceptions;

/**
 * Class Name: InvalidIDException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid identifier.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

@SuppressWarnings("serial")
public class InvalidIDException extends Exception {

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid identifier was found
	 * @param line The line that the invalid identifier was found
	 */
	public InvalidIDException(String filename, int line){
		
		super("Error! Invalid record`s ID (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
	
}