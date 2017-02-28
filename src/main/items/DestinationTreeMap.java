package main.items;

import main.exceptions.DuplicateIDException;
import main.io.DataFileReader;

import java.util.TreeMap;

/**
 * Class Name: DestinationTreeMap.java
 * 
 * Description: This class creates two TreeMaps 
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class DestinationTreeMap {
	
	private TreeMap<Integer, Destination> destinations; //The TreeMap that contains all the destinations
	
	/**
	 * This constructor creates a new TreeMap that contains all the
	 * destinations of 2017.
	 * 
	 * @param destinations TreeMap with destinations
	 */
	public DestinationTreeMap(TreeMap<Integer, Destination> destinations){
	
		this.destinations = destinations;
		
	}
	
	/**
	 * This method puts a destination to a TreeMap if it does not
	 * already exist.
	 * 
	 * @param destination The destination object
	 * @throws DuplicateIDException If a destination with same ID already exists show an error
	 */
	public void addDestination2017(Destination destination) throws DuplicateIDException{

		if(destinations.containsKey(destination.getDestinationID())){
			try {
				throw new DuplicateIDException(DataFileReader.FILE_NAME_DESTINATIONS_2017, DataFileReader.line_counter);
			} catch (DuplicateIDException e) {
				System.out.println(e.getMessage());
			}
		} else {
			destinations.put(destination.getDestinationID(), destination);
		}
	}
	
	/**
	 * This method checks if the destination object already
	 * exists.
	 * 
	 * @param destination The destination object
	 * @return boolean true/false
	 */
	public boolean containsDestinationName(Destination destination) {
		
		for(Destination d : destinations.values()) {
			if(d.getDestinationName().equals(destination.getDestinationName())){
				return true;
			}
		}
		return false;
	}
	
	/* Getters and Setters */
	
	public TreeMap<Integer, Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(TreeMap<Integer, Destination> destinations) {
		if(destinations != null && destinations.size() > 0 )
			this.destinations = destinations;
	}
}
