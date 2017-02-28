package main.exceptions;

/**
 * Class Name: InvalidUrbanException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid urban identifier.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


@SuppressWarnings("serial")
public class InvalidUrbanException extends Exception {

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid urban identifier was found
	 * @param line The line that the invalid urban identifier was found
	 */
	public InvalidUrbanException (String filename, int line){
  
		super("Error! Wrong urban identifier (in file " + filename + " in line: " + Integer.toString(line) + " ).");
  
	}
}