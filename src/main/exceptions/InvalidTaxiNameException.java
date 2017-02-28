package main.exceptions;

/**
 * Class Name: InvalidTaxiNameException.java
 * 
 * Description: This class is creating an exception for records that have 
 * 				invalid taxi name.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


@SuppressWarnings("serial")
public class InvalidTaxiNameException extends Exception{
	
	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid taxi name was found
	 * @param line The line that the invalid taxi name was found
	 */
	public InvalidTaxiNameException(String filename, int line){
		
		super("Error! Wrong driver`s name (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}
}
