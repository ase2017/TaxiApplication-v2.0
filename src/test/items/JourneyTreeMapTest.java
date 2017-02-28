package test.items;

import main.items.Journey;
import main.items.JourneyTreeMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Class Name: JourneyTreeMapTest.java
 *
 * Description: This class includes JUnit tests for the class JourneyTreeMap.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


public class JourneyTreeMapTest {

    private int trueDestinationID = 1; //An example of a correct destination ID
    private String trueTaxiRegistrationNumber = "AA111AA"; //An example of a correct registration number
    private int trueNumberOfPassengers = 2; //An example of a correct number of passengers
    private double trueTime = 11; //An example of a correct duration
    private double trueMaximumVelocity = 10; //An example of a correct maximum velocity

    /**
     * This method tries to push in the TreeMap a null Journey Object.
     */
    @Test(expected = NullPointerException.class)
    public void testNullJourneyAddJourney(){

        TreeMap<String, ArrayList<Journey>> journeys = new TreeMap<String, ArrayList<Journey>>();
        JourneyTreeMap jtmp = new JourneyTreeMap(journeys);
        Journey jn = null;

        jtmp.addJourney(jn);
    }

    /**
     * This method tries to push in the TreeMap a null ArrayList of Journeys.
     */
    @Test(expected = NullPointerException.class)
    public void testNullArrayListAddJourney(){

        TreeMap<String, ArrayList<Journey>> journeys = new TreeMap<String, ArrayList<Journey>>();
        ArrayList<Journey> aj = new ArrayList<Journey>();
        aj = null;
        JourneyTreeMap jtmp = new JourneyTreeMap(journeys);

        jtmp.addJourney(aj);
    }

    /**
     * This method tries to push in the TreeMap an empty ArrayList of Journeys.
     */
    @Test(expected = NullPointerException.class)
    public void testEmptyArrayListAddJourney(){

        TreeMap<String, ArrayList<Journey>> journeys = new TreeMap<String, ArrayList<Journey>>();
        ArrayList<Journey> aj = new ArrayList<Journey>();
        JourneyTreeMap jtmp = new JourneyTreeMap(journeys);

        jtmp.addJourney(aj);
    }

    /**
     * This method tries to create a new JourneyTreeMap and pushes a new Journey in it.
     */
    @Test
    public void trueCaseExample(){

        TreeMap<String, ArrayList<Journey>> journeys = new TreeMap<String, ArrayList<Journey>>();
        JourneyTreeMap jtmp = new JourneyTreeMap(journeys);
        Journey jn = new Journey( trueDestinationID, trueTaxiRegistrationNumber, trueNumberOfPassengers,
                trueTime, trueMaximumVelocity);

        jtmp.addJourney(jn);

        assertEquals(1, jtmp.getJourneys().size());
    }

    /**
     * This method tries to create a new JourneyTreeMap and pushes a new ArrayList with journeys in it.
     */
    @Test
    public void trueCaseExampleArrayList(){

        TreeMap<String, ArrayList<Journey>> journeys = new TreeMap<String, ArrayList<Journey>>();
        JourneyTreeMap jtmp = new JourneyTreeMap(journeys);
        ArrayList<Journey> ajn = new ArrayList<Journey>();

        Journey jn = new Journey( trueDestinationID, trueTaxiRegistrationNumber, trueNumberOfPassengers,
                trueTime, trueMaximumVelocity);

        ajn.add(jn);
        jtmp.addJourney(ajn);

        assertEquals(1, jtmp.getJourneys().size());
    }

}
