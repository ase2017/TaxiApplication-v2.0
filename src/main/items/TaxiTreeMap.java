package main.items;

import main.exceptions.DuplicateIDException;
import main.io.DataFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Name: TaxiTreeMap.java
 * 
 * Description: This class creates a TreeMap in order to store
 * 				taxi Objects.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class TaxiTreeMap {
	
	private TreeMap<String, Taxi> taxis; //The TreeMap that contains the taxis

	/**
	 * This constructor creates a new TaxiTreeMap.
	 * 
	 * @param taxis TreeMap that contains all the taxis
	 */
	public TaxiTreeMap(TreeMap<String, Taxi> taxis){

		this.taxis = taxis;
		
	}

	/**
	 * Method that checks if a taxi with the same details already exists and
	 * if not it adds it in the TreeMap.
	 * 
	 * @throws DuplicateIDException If a taxi with the same ID already exists, show an error message
	 * 
	 * @param taxi The taxi object that we want to push
	 */
	public void addTaxi(Taxi taxi){
		
		if(taxis.containsKey(taxi.getRegistrationNumber())){
			try {
				throw new DuplicateIDException(DataFileReader.FILE_NAME_TAXIS, DataFileReader.line_counter);
			} catch (DuplicateIDException e) {
				System.out.println(e.getMessage()); //Show error message
			}
		} else if(taxis == null){
				throw new NullPointerException();
		}else {
			taxis.put(taxi.getRegistrationNumber(), taxi);
		}
		
	}

	/**
	 * @author Jules
	 * Sorts the taxis by name and returns an treemap of arralylists of them
	 * @return an Arraylist of taxis, sorted by name
	 */
	public ArrayList<Taxi> sortTaxisByName() {

		ArrayList<Taxi> sortedTaxi = new ArrayList<>();


		if(taxis != null && this.getTaxis() != null && this.getTaxis().size() > 0) {
			for(Map.Entry<String,Taxi> mapItem : this.getTaxis().entrySet()) {
				sortedTaxi.add(mapItem.getValue());
			}
		}

		//sorting
		Collections.sort(sortedTaxi,
				(o1, o2) -> ((Taxi) o1).getDriverName().compareTo(((Taxi) o2).getDriverName()));

		return sortedTaxi;
	}
	
	/* Getters and Setters */
	
	public TreeMap<String, Taxi> getTaxis() {
		return taxis;
	}

	public void setTaxis(TreeMap<String, Taxi> taxis) {

		if(taxis != null && taxis.size() > 0 )
			this.taxis = taxis;
	}
}
