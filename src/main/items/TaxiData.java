package main.items;

import java.util.*;

/**
 * Class that contains all the data related to taxis, destinations and journeys
 */
public class TaxiData {

	private TaxiTreeMap taxis;
	private DestinationTreeMap currentYearDestinations;
	private DestinationTreeSet previousYearDestinations;
	private JourneyTreeMap journeys;
	public static int topNJourneys = 5;

	/**
	 * Formats and returns a line of information for the first output file (top 5 journeys)
	 * @param taxi a Taxi object
	 * @param destination a Destination
	 * @param journey a Journey
	 * @return a String containing a line of information for the first output file (top 5 journeys)
	 */
	public String formatInfoLine(Taxi taxi, Destination destination, Journey journey){

		String res = "";
		if(taxi != null && destination != null && journey != null) {
			res += journey.getTaxiRegistrationNumber() + "\t\t"
					+ String.format("%1$.1f",
					destination.getDistance())
					+ ((destination.getDistance() >= 2) ? " miles" : " mile ")  +"\t"
					+ journey.getNumberOfPassengers()
					+ ((journey.getNumberOfPassengers() > 1) ? " passengers" : " passenger ") + "\t"
					+  String.format("Cost £%1$.1f",
					calculateFee(journey)) + "\t"
					+ (destination.isUrban() ? "urban" : "rural") + "\t"
					+  String.format("%1$.1f mph",journey.getMaximumVelocity()) + "\t"
					+ destination.getDestinationName() + "\t"
					+ "\n";
		}
		return res;
	}

	/**
	 * Function to be called to generate the content of the first output file
	 * @return a String containing the content of the first output file
	 */
	public String formatJourneyFile(){

		return (formatTopJourneys(topNJourneys,true)
				+ formatTopJourneys(topNJourneys,false));

	}


	/**
	 * Formats and returns the top most or cheapest expensive given number of journeys
	 * @param numberOfJourneys : the number of top journeys that we are interested in
	 * @param mostExpensive if true, will get the top N most expensive. If false, the top N cheapest.
     * @return a String, which is the content of the first or the last part of the first output file
     */
	public String formatTopJourneys(int numberOfJourneys, boolean mostExpensive){

		String result = "";

		if(numberOfJourneys <= 0) {
			result = "Invalid input number of journeys.";
		} else {
			if (journeys == null || journeys.getJourneys() == null || journeys.getJourneys().size() == 0) {
				result = "There was no journey in our records.";
			} else {

				// Need to store them in treemap of arraylist (key = price) because some journeys could have the same price
				// Also treemap orders keys which is useful
				TreeMap<Double,ArrayList<Journey>> sortedJourneysByPrice = getJourneysByPrice(mostExpensive);

				ArrayList<Journey> topNjourneys = new ArrayList<>();

				Iterator it = sortedJourneysByPrice.values().iterator();

				int maxSize = (numberOfJourneys <= journeys.getJourneys().size() ? numberOfJourneys : journeys.getJourneys().size());



				ArrayList<Journey> temporaryJourneyArrayList; // for the loop

				// getting top / cheapest N journeys (or less, if there are less journeys than N)
				while(it.hasNext() && topNjourneys.size() < maxSize) {
					temporaryJourneyArrayList = (ArrayList<Journey>)it.next();
					for (Journey j : temporaryJourneyArrayList) {
						// we only add the journey if we did not reach the max size (maxSize)
						// and if there is Destination and a Taxi related to that Journey
						if(topNjourneys.size() < maxSize && findDestination(j) != null && findTaxi(j) != null)
							topNjourneys.add(j);

						// if we reached maxSize we stop the for loop
						if (topNjourneys.size() >= maxSize)
							break;
					}
				}

				result = "CHARGES FOR THE " + (mostExpensive ? "TOP " : "CHEAPEST ") + topNjourneys.size() + " JOURNEYS\n";

				// formatting to String for each line
				if(mostExpensive) {
					for(int i = 0; i < topNjourneys.size(); i++){
						Destination dest = findDestination(topNjourneys.get(i));
						Taxi tx = findTaxi(topNjourneys.get(i));
						result += formatInfoLine(tx,dest,topNjourneys.get(i));
					}
				} else {
					for(int i = topNjourneys.size()-1; i >= 0; i--){
						Destination dest = findDestination(topNjourneys.get(i));
						Taxi tx = findTaxi(topNjourneys.get(i));
						result += formatInfoLine(tx,dest,topNjourneys.get(i));
					}
				}
			}
		}
		return  result + "\n";
	}



	/**
	 *	Calculates and returns the fee of a Journey
	 * @param journey the Journey that we are interested in
	 * @return the fee of a given journey
	 * Requirement : it is expected that Journey and Destination information is correct
	 */
	public double calculateFee(Journey journey){

		double POUNDS_PER_MILE = 1.0;
		double POUNDS_PER_MINUTE = 0.5;
		double CHARGE_COEFFICIENT = 1.0;

		if(journey != null) {
			Destination dest = findDestination(journey);

			if(dest != null) {

				if  (dest.isUrban())
					CHARGE_COEFFICIENT = 1.1;

				// The taxi company takes the highest fee of both possible fees
				if (POUNDS_PER_MILE * dest.getDistance() < POUNDS_PER_MINUTE * journey.getTime()) {
					return CHARGE_COEFFICIENT * (3 + POUNDS_PER_MINUTE * journey.getTime() + 0.5 * journey.getNumberOfPassengers());
				} else {
					return CHARGE_COEFFICIENT * (3 + POUNDS_PER_MILE * dest.getDistance() + 0.5 * journey.getNumberOfPassengers());
				}
			}

		}
		return -1; // -1 is returned if there was an issue
	}

	/**
	 * Returns a treemap of arraylist of journeys, with keys being the price
	 * @param mostExpensive If true gets the most expensive journeys otherwise the cheapest
	 * @return a treemap of arraylist of journeys, with keys being the price
	 */
	public TreeMap<Double,ArrayList<Journey>> getJourneysByPrice(boolean mostExpensive) {

		TreeMap<Double,ArrayList<Journey>> topNJourneysWithSamePrice = new TreeMap<>();

		if(mostExpensive)
			topNJourneysWithSamePrice = new TreeMap<>((Collections.reverseOrder()));

		for(Map.Entry<String,ArrayList<Journey>> entry : journeys.getJourneys().entrySet()) {
			for(Journey j : entry.getValue()) {
				double fee = calculateFee(j);

				// if it was possible to calculate the fee, we add that journey
				if(fee != -1) {
					if(topNJourneysWithSamePrice.containsKey(calculateFee(j))) {
						topNJourneysWithSamePrice.get(calculateFee(j)).add(j);
					} else {
						ArrayList<Journey> tmpJourneys = new ArrayList<>();
						topNJourneysWithSamePrice.put(calculateFee(j),tmpJourneys);
						topNJourneysWithSamePrice.get(calculateFee(j)).add(j);
					}
				}
			}
		}
		return topNJourneysWithSamePrice;
	}


	/**
	 * Finds and returns the taxi associated to the given journey
	 * @param journey the journey object, from which we try to find the Taxi
	 * @return a Taxi object related to the given journey
	 */
	public Taxi findTaxi(Journey journey){

		if(journey != null && taxis != null && taxis.getTaxis() != null && taxis.getTaxis().containsKey(journey.getTaxiRegistrationNumber())) {
			return (taxis.getTaxis().get(journey.getTaxiRegistrationNumber()));
		}

		return null;
	}

	/**
	 * Returns the destination object related to the given journey
	 * @param journey : the given journey
	 * @return the destination object related to the given journey
	 */
	public Destination findDestination(Journey journey){

		if(journey != null && currentYearDestinations != null && currentYearDestinations.getDestinations() != null
				&& currentYearDestinations.getDestinations().containsKey(journey.getDestinationID())) {
			return (currentYearDestinations.getDestinations().get(journey.getDestinationID()));
		}

		return null;
	}

	/**
	 * Formats the second file, which contains the list of places visited per driver, ordered alphabetically by driver name
	 * @return a String containing the content of the file
	 */
	public String formatPlacesVisitedPerDriver(){

		String res = "PLACES VISITED PER DRIVER\n\n";

		if(taxis != null && taxis.getTaxis() != null && taxis.getTaxis().size() > 0) {
			ArrayList<Taxi> sortedTaxi = taxis.sortTaxisByName();

			// Each value of the tree map is an arraylist
			// TreeMap keySet is sorted alphabetically
			for(Taxi t : sortedTaxi) {

				// driver name
				res += t.getDriverName() + "\n";

				ArrayList<Journey> journeysOfCurrentTaxi = findJourneys(t); //journeys of the driver

				if (journeysOfCurrentTaxi != null && journeysOfCurrentTaxi.size() > 0) {
					TreeSet<String> destinationNamesSorted = new TreeSet<String>(); //sorted distance name of the journeys of the driver

					// getting all distances done by driver
					for (Journey j : journeysOfCurrentTaxi) {
						Destination tmpDestination = findDestination(j);

						if (tmpDestination != null) {
							destinationNamesSorted.add(tmpDestination.getDestinationName());
						} else {
							System.out.println("No destinations were found in our records for given journey.\n");
						}
					}

					// building up the result
					for (String destinationName : destinationNamesSorted) {
						res += "\t " + destinationName + "\n";
					}
				} else {
					res += "No journeys / destinations were found in our records for this taxi.\n";
				}

				res += "\n"; //separation between each taxi

			}

		} else {
			res += "No taxis were found in our records.\n";
		}

		return res;
	}


	/**
	 * Returns the list of journeys made by a given taxi for the current year
	 * @param taxi : the given taxi
	 * @return an arraylist of the journeys made by the given taxi
	 */
	public ArrayList<Journey> findJourneys(Taxi taxi){

		if(taxi != null && journeys != null && journeys.getJourneys() != null && journeys.getJourneys().containsKey(taxi.getRegistrationNumber())) {
			return (journeys.getJourneys().get(taxi.getRegistrationNumber()));
		}

		return null;
	}

	/**
	 * Formats the content of file number 3, which contains the places visited per year and in both years
	 * @return a String of the content to be put in the third file
	 */
	public String formatPlacesVisited(){
		String res = "";

		if(previousYearDestinations!= null && previousYearDestinations.getDestinations() != null && previousYearDestinations.getDestinations().size() > 0
				&& currentYearDestinations != null && currentYearDestinations.getDestinations() != null && currentYearDestinations.getDestinations().size() > 0) {

			TreeSet<String> currentYearVisitedPlacesSet  = new TreeSet<>();
			TreeSet<String> previousYearVisitedPlacesSet  = new TreeSet<>();
			TreeSet<String> placesVisitedInBothYearsSet  = new TreeSet<>();

			for(Destination l : currentYearDestinations.getDestinations().values()) {
				if(previousYearDestinations.containsDestinationName(l)) {
					placesVisitedInBothYearsSet.add(l.getDestinationName());
				} else {
					currentYearVisitedPlacesSet.add(l.getDestinationName());
				}
			}

			for(Destination l : previousYearDestinations.getDestinations()) {
				if(currentYearDestinations.containsDestinationName(l)) {
					// do nothing, it's already added in previous loop
				} else {
					previousYearVisitedPlacesSet.add(l.getDestinationName());
				}
			}

			res += formatCurrentYearVisitedPlaces(currentYearVisitedPlacesSet)
					+ formatPreviousYearVisitedPlaces(previousYearVisitedPlacesSet)
					+ formatPlacesVisitedInBothYears(placesVisitedInBothYearsSet);

		} else {
			res = "At least one destinations file was empty";
		}

		return res;
	}

	/**
	 * Formats and returns the first part of the third file about destinations per year
	 * @param currentYearVisitedPlacesSet the (unique) places visited this year
	 * @return the first part of the third file about destinations per year
	 */
	public String formatCurrentYearVisitedPlaces(TreeSet<String> currentYearVisitedPlacesSet){

		String res = "";

		if(currentYearVisitedPlacesSet != null && currentYearVisitedPlacesSet.size() > 0) {
			res = currentYearVisitedPlacesSet.size()
					+ ((currentYearVisitedPlacesSet.size() > 1) ? " NEW PLACES IN 2017\n" : " NEW PLACE IN 2017\n");

			for(String str : currentYearVisitedPlacesSet) {
				res += str + "\n";
			}
			res += "\n";
		} else {
			res = "Sorry, no destinations were found in our record of current year.\n\n";
		}

		return res;
	}


	/**
	 * Formats and returns the second part of the third file about destinations per year
	 * @param previousYearVisitedPlacesSet  the (unique) places visited least year
	 * @return the second part of the third file about destinations per year
	 */
	public String formatPreviousYearVisitedPlaces(TreeSet<String> previousYearVisitedPlacesSet){

		String res = "";

		if(previousYearVisitedPlacesSet != null && previousYearVisitedPlacesSet.size() > 0) {

			res = previousYearVisitedPlacesSet.size()
					+ ((previousYearVisitedPlacesSet.size() > 1) ? " PLACES VISITED IN 2016 ONLY\n" : " PLACE VISITED IN 2016 ONLY\n");

			for(String str : previousYearVisitedPlacesSet) {
				res += str + "\n";
			}
			res += "\n";

		} else {
			res = "Sorry, no destinations were found in our record of previous year.\n\n";
		}

		return res;
	}


	/**
	 * Formats and returns the third part of the third file about destinations per year
	 * @param placesVisitedInBothYearsSet the (unique) places visited in both years
	 * @return the third part of the third file about destinations per year
	 */
	public String formatPlacesVisitedInBothYears(TreeSet<String> placesVisitedInBothYearsSet){

		String res = "";

		if(placesVisitedInBothYearsSet != null && placesVisitedInBothYearsSet.size() > 0) {

			res = placesVisitedInBothYearsSet.size()
					+ ((placesVisitedInBothYearsSet.size() > 1) ? " PLACES VISITED IN BOTH 2017 AND 2016\n" : " PLACE VISITED IN BOTH 2017 AND 2016\n");

			for(String str : placesVisitedInBothYearsSet) {
				res += str + "\n";
			}
			res += "\n";

		} else {
			res = "Sorry, no destinations were found in our record of both current and previous years.\n\n";
		}

		return res;
	}

	/**
	 * Checks that all 4 collections contain at least one element
	 * @return true/false
     */
	public boolean checkAllCollectionsAreInitialized() {
		if (this != null && this.getTaxis() != null && this.getCurrentYearDestinations() != null
				&& this.getPreviousYearDestinations() != null && this.getJourneys() != null
				&& this.getTaxis().getTaxis() != null
				&& this.getPreviousYearDestinations().getDestinations() != null
				&& this.getJourneys().getJourneys() != null && this.getCurrentYearDestinations() != null
				&& this.getCurrentYearDestinations().getDestinations().size() > 0
				&& this.getJourneys().getJourneys().size() > 0
				&& this.getPreviousYearDestinations().getDestinations().size() > 0
				&& this.getTaxis().getTaxis().size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/* Getters and Setters */

	public JourneyTreeMap getJourneys() {
		return journeys;
	}

	public void setJourneys(JourneyTreeMap journeys) {
		this.journeys = journeys;
	}

	public TaxiTreeMap getTaxis() {
		return taxis;
	}

	public void setTaxis(TaxiTreeMap taxis) {
		this.taxis = taxis;
	}

	public DestinationTreeMap getCurrentYearDestinations() {
		return currentYearDestinations;
	}

	public void setCurrentYearDestinations(DestinationTreeMap currentYearDestinations) {
		this.currentYearDestinations = currentYearDestinations;
	}

	public DestinationTreeSet getPreviousYearDestinations() {
		return previousYearDestinations;
	}

	public void setPreviousYearDestinations(DestinationTreeSet previousYearDestinations) {
		this.previousYearDestinations = previousYearDestinations;
	}
}
