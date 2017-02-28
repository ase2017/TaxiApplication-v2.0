package test.items;

import main.exceptions.DuplicateIDException;
import main.items.Destination;
import main.items.DestinationTreeMap;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Class Name: DestinationTreeMapTest.java
 *
 * Description: This class includes JUnit tests for the class DestinationTreeMap.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class DestinationTreeMapTest {

    private int trueDestinationID = 1; //Example of correct destination ID
    private String trueDestinationName = "Some Street"; //Example of correct destination name
    private double trueDistance = 24.2; //Example of correct destination distance
    private boolean trueUrban = true; //Example of correct urban identifier

    /**
     * This method tries to push in the TreeMap two destinations with the same ID.
     *
     * @throws DuplicateIDException In case that there are two Objects with the same ID
     */
    @Test
    public void testAddDestination2017() throws DuplicateIDException{

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(serialContent));

        TreeMap<Integer, Destination> destinations = new TreeMap<Integer, Destination>();
        Destination d1 = new Destination(trueDestinationID, trueDestinationName, trueDistance, trueUrban);
        Destination d2 = new Destination(trueDestinationID, trueDestinationName, trueDistance, trueUrban);
        DestinationTreeMap dtmp = new DestinationTreeMap(destinations);

        dtmp.addDestination2017(d1);
        dtmp.addDestination2017(d2);

        assertEquals(1, dtmp.getDestinations().values().size());
    }

    /**
     * This method tries to create a new DestinationTreeMap and pushes a new Destination in it.
     *
     * @throws DuplicateIDException In case that there are two Objects with the same ID
     */
    @Test
    public void testTrueCase() throws DuplicateIDException {

        TreeMap<Integer, Destination> destinations = new TreeMap<Integer, Destination>();
        Destination d1 = new Destination(trueDestinationID, trueDestinationName, trueDistance, trueUrban);
        DestinationTreeMap dtmp = new DestinationTreeMap(destinations);
        dtmp.addDestination2017(d1);

        assertEquals(1, dtmp.getDestinations().values().size());
    }
}
