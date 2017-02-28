package main.exceptions;

/**
 * Class Name: InvalidInputArgumentsException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				less/more data than they should.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

@SuppressWarnings("serial")
public class InvalidInputArgumentsException extends Exception{

	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid record was found
	 * @param line The line that the invalid record was found
	 */
	public InvalidInputArgumentsException(String filename, int line){
		
		super("Error! The input arguments are invalid (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
}
