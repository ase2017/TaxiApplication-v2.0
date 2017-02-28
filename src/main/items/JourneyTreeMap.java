package main.items;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Class Name: JourneyTreeMap.java
 * 
 * Description: This class creates a TreeMap in order to store
 * 				Journey Objects.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class JourneyTreeMap {
	
	private TreeMap<String, ArrayList<Journey>> journeys; //The TreeMap that contains all the journeys
	
	/**
	 * This is the constructor of this class.
	 * 
	 * @param journeys The TreeMap that contains the journeys.
	 */
	public JourneyTreeMap(TreeMap<String, ArrayList<Journey>> journeys){
		
		this.journeys = journeys;
		
	}
	
	/**
	 * This method adds a new journey to the ArrayList.
	 * 
	 * @param journey The journey object that we want to push
	 */
	public void addJourney(Journey journey) {

		if(journey == null){
			throw new NullPointerException();
		} else if(journeys.containsKey(journey.getTaxiRegistrationNumber())) {
			journeys.get(journey.getTaxiRegistrationNumber()).add(journey);
		} else {
			ArrayList<Journey> temporary = new ArrayList<>();
			temporary.add(journey);
			journeys.put(journey.getTaxiRegistrationNumber(), temporary);
		}
	}

	/**
	 * This method transfers all the information which is included in
	 * an ArrayList to the TreeMap.
	 * 
	 * @param journey The ArrayList that contains the data of the journeys
	 */
	public void addJourney(ArrayList<Journey> journey){

		if(journey == null || journey.size() == 0) {
			throw new NullPointerException();
		}else{
			for (int i = 0; i < journey.size(); i++) {
				addJourney(journey.get(i));
			}
		}
	}

	/* Getters and Setters */
	
	public TreeMap<String, ArrayList<Journey>> getJourneys() {
		return journeys;
	}

	public void setJourneys(TreeMap<String, ArrayList<Journey>> journeys) {

		if(journeys != null && journeys.size() > 0)
			this.journeys = journeys;
	}
}