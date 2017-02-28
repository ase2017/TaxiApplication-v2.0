package test.items;

import main.exceptions.DuplicateIDException;
import main.items.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class that tests some TaxiData methods
 *
 * @author Jules
 */
public class TaxiDataTest{

    private TaxiData taxiData;

    // Destinations
    private Destination nullDestination = null;
    private Destination nonNullDestination;

    // Journeys
    private Journey nullJourney = null;
    private Journey nonNullJourney;
    private Journey journeyWithDestination;
    private Journey journeyWithoutDestination;
    private Journey journeyWithTaxi;
    private Journey journeyWithoutTaxi;

    // Taxis
    private Taxi nullTaxi = null;
    private Taxi nonNullTaxi;
    private Taxi taxiWithJourneys;
    private Taxi taxiWithoutJourneys;


    /**
     * Preparation of the tests. Fills the attributes with objects re-used during tests.
     */
    @Before
    public void setUp() {

        taxiData = new TaxiData();

        /* TAXIS */
        TreeMap<String,Taxi> taxis = new TreeMap<>();
        TaxiTreeMap taxiTreeMap = new TaxiTreeMap(taxis);

        taxiTreeMap.addTaxi(new Taxi("BR204SV","Eric Smith","Mercedes"));
        taxiTreeMap.addTaxi(new Taxi("CO367DG","Giovanni Campbell","Mercedes"));
        taxiTreeMap.addTaxi(new Taxi("AA743HE","Antonio Ivanov","Toyota"));
        taxiTreeMap.addTaxi(new Taxi("AC123UV","Alice Smith","Nissan"));
        taxiTreeMap.addTaxi(new Taxi("AC321RE","Nicholas Ivanov","Toyouta"));
        taxiTreeMap.addTaxi(new Taxi("zz999zz","Jack Hunter","Mercedes")); // taxi without journey

        taxiData.setTaxis(taxiTreeMap);

        /* ************************************************* */

        /* DESTINATIONS 2017 */
        TreeMap<Integer,Destination> destinations = new TreeMap<>();
        DestinationTreeMap currentYearDestinations = new DestinationTreeMap(destinations);
        try {
            currentYearDestinations.addDestination2017(new Destination(1, "Palace of Holyroodhouse", 2.9, false));
            currentYearDestinations.addDestination2017(new Destination(2, "Heriot Watt University", 10.3, true));
            currentYearDestinations.addDestination2017(new Destination(3, "National Museum of Scotland", 6.3, false));
            currentYearDestinations.addDestination2017(new Destination(4, "Edinburgh Airport", 3.6, true));
            currentYearDestinations.addDestination2017(new Destination(5, "NO JOURNEY", 3.6, true));
        } catch(DuplicateIDException e) {

        }

        taxiData.setCurrentYearDestinations(currentYearDestinations);

        /* ************************************************* */

        /* DESTINATIONS 2016 */
        TreeSet<Destination> lastYearDestinations = new TreeSet<>();
        DestinationTreeSet previousYearDestinations = new DestinationTreeSet(lastYearDestinations);

        previousYearDestinations.add(new Destination("Main Station"));
        previousYearDestinations.add(new Destination("Napier University"));
        previousYearDestinations.add(new Destination("Main Station"));
        previousYearDestinations.add(new Destination("BT Murrayfield Stadium"));

        taxiData.setPreviousYearDestinations(previousYearDestinations);


        /* ************************************************* */

        /* JOURNEYS */
        TreeMap<String,ArrayList<Journey>> journeys = new TreeMap<>();
        JourneyTreeMap journeyTreeMap = new JourneyTreeMap(journeys);

        journeyTreeMap.addJourney(new Journey(1,"BR204SV",2,15,43.4));
        journeyTreeMap.addJourney(new Journey(2,"BR204SV",3,33,52.3));
        journeyTreeMap.addJourney(new Journey(3,"CO367DG",2,21,39.1));
        journeyTreeMap.addJourney(new Journey(4,"BR204SV",1,8,60.7));
        journeyTreeMap.addJourney(new Journey(55555,"ZZ367ZZ",5,8,78.3)); // journey without destination. Index 4
        journeyTreeMap.addJourney(new Journey(6,"ZZ367aa",5,8,78.3)); // journey without taxi

        taxiData.setJourneys(journeyTreeMap);


        /* ****************************************** */

        journeyWithDestination = taxiData.getJourneys().getJourneys().get("BR204SV").get(0);
        journeyWithoutDestination = taxiData.getJourneys().getJourneys().get("ZZ367ZZ").get(0);
        journeyWithTaxi = taxiData.getJourneys().getJourneys().get("BR204SV").get(0);;
        journeyWithoutTaxi = taxiData.getJourneys().getJourneys().get("ZZ367aa").get(0);;


        nonNullTaxi = taxiData.getTaxis().getTaxis().get("BR204SV");
        nonNullDestination = taxiData.getCurrentYearDestinations().getDestinations().get(1);
        nonNullJourney = taxiData.getJourneys().getJourneys().get("BR204SV").get(0);

        taxiWithJourneys = taxiData.getTaxis().getTaxis().get("BR204SV");
        taxiWithoutJourneys = taxiData.getTaxis().getTaxis().get("zz999zz");
    }


    @Test
    public void formatInfoLine() {



        // 3 non null objects
        assertNotEquals("",taxiData.formatInfoLine(nonNullTaxi,nonNullDestination,nonNullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nonNullTaxi,nonNullDestination,nonNullJourney));

        // 3 null objects
        assertEquals("",taxiData.formatInfoLine(nullTaxi,nullDestination,nullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nullTaxi,nullDestination,nullJourney));

        // taxi and destination are null
        assertEquals("",taxiData.formatInfoLine(nullTaxi,nullDestination,nonNullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nullTaxi,nullDestination,nonNullJourney));

        // journey and destination are null
        assertEquals("",taxiData.formatInfoLine(nonNullTaxi,nullDestination,nullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nonNullTaxi,nullDestination,nullJourney));

        // taxi and journey are null
        assertEquals("",taxiData.formatInfoLine(nullTaxi,nonNullDestination,nullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nullTaxi,nonNullDestination,nullJourney));

        // taxi is null
        assertEquals("",taxiData.formatInfoLine(nullTaxi,nonNullDestination,nonNullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nullTaxi,nonNullDestination,nonNullJourney));

        // destination is  null
        assertEquals("",taxiData.formatInfoLine(nonNullTaxi,nullDestination,nonNullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nonNullTaxi,nullDestination,nonNullJourney));

        // journey is null
        assertEquals("",taxiData.formatInfoLine(nonNullTaxi,nonNullDestination,nullJourney));
        assertNotEquals(null,taxiData.formatInfoLine(nonNullTaxi,nonNullDestination,nullJourney));


    }

    @Test
    public void formatJourneyFile() {
        assertNotEquals(null,taxiData.formatJourneyFile());
        assertNotEquals("",taxiData.formatJourneyFile());
    }

    @Test
    public void formatTopJourneys() {

        // N < number of journeys stored
        assertNotEquals(null,taxiData.formatTopJourneys(5,true));
        assertNotEquals("",taxiData.formatTopJourneys(5,true));
        assertNotEquals(null,taxiData.formatTopJourneys(5,false));
        assertNotEquals("",taxiData.formatTopJourneys(5,false));

        // N > number of journeys stored
        assertNotEquals(null,taxiData.formatTopJourneys(1000,true));
        assertNotEquals("",taxiData.formatTopJourneys(1000,true));
        assertNotEquals(null,taxiData.formatTopJourneys(1000,false));
        assertNotEquals("",taxiData.formatTopJourneys(1000,false));

        // N = 0
        assertEquals("Invalid input number of journeys.\n",taxiData.formatTopJourneys(0,true));
        assertEquals("Invalid input number of journeys.\n",taxiData.formatTopJourneys(0,false));

        // N invalid (negative)
        assertEquals("Invalid input number of journeys.\n",taxiData.formatTopJourneys(-1,true));
        assertEquals("Invalid input number of journeys.\n",taxiData.formatTopJourneys(-1,false));
    }




    @Test
    public void calculateFee() {

        // Testing : Journey is null
        assertEquals(-1,taxiData.calculateFee(nullJourney),0.0);

        // Testing : Journey has a related Destination
        assertNotEquals(-1,taxiData.calculateFee(journeyWithDestination),0.0);

        // Testing : Journey does not have a related Destination
        assertEquals(-1,taxiData.calculateFee(journeyWithoutDestination),0.0);
    }




    @Test
    public void getJourneysByPrice() {
        // No useful tests
    }

    @Test
    public void findTaxi() {

        // Testing : Journey is null
        assertEquals(null,taxiData.findTaxi(nullJourney));

        // Testing : Journey is not null and has a Taxi
        assertNotEquals(null,taxiData.findTaxi(journeyWithTaxi));

        // Testing : Journey is not null but does not have a Taxi
        assertEquals(null,taxiData.findTaxi(journeyWithoutTaxi));
    }

    @Test
    public void findDestination() {

        // Testing : Journey is null
        assertEquals(null,taxiData.findDestination(nullJourney));

        // Testing : Journey is not null and has a Destination
        assertNotEquals(null,taxiData.findDestination(journeyWithDestination));

        // Testing : Journey is not null but does not have a Destination
        assertEquals(null,taxiData.findDestination(journeyWithoutDestination));

    }

    @Test
    public void formatPlacesVisitedPerDriver() {
        // No useful tests
    }


    @Test
    public void findJourneys() {

        // Testing : Taxi is null
        assertEquals(null,taxiData.findJourneys(nullTaxi));

        // Testing : Taxi is not null and has at least one Journey
        assertNotEquals(null,taxiData.findJourneys(taxiWithJourneys));
        assertNotEquals(0,taxiData.findJourneys(taxiWithJourneys).size());

        // Testing : Taxi is not null but does not have a Journey
        assertEquals(null,taxiData.findJourneys(taxiWithoutJourneys));

    }

    @Test
    public void formatPlacesVisited() {
        // No useful tests
    }

    @Test
    public void formatCurrentYearVisitedPlaces() {
        // No useful tests
    }

    @Test
    public void formatPreviousYearVisitedPlaces() {
        // No useful tests
    }





}
