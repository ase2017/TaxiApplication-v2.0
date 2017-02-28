package main.exceptions;

/**
 * Class Name: InvalidBrandNameException.java
 * 
 * Description: This class is creating an exception if the brand name
 * 				of the taxi is invalid.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


@SuppressWarnings("serial")
public class InvalidBrandNameException extends Exception{
	
	/**
	 * This is the constructor of this exception.
	 * 
	 * @param filename The filename in which the invalid brand name was found
	 * @param line The line that the invalid brand name id was found
	 */
	public InvalidBrandNameException(String filename, int line){
		
		super("Error! Wrong car`s brand (in file " + filename + " in line: " + Integer.toString(line) + " ).");
		
	}

}
