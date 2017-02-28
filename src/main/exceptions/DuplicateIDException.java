package main.exceptions;

/**
 * Class Name: DuplicateIDException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				the	same identifier.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

@SuppressWarnings("serial")
public class DuplicateIDException extends Exception {

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the duplicate id was found
	 * @param line The line that the duplicate id was found
	 */
	public DuplicateIDException(String filename, int line){
		
		super("Error! Duplicate item ID (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
	 
	
}
