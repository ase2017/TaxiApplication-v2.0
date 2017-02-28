package test.items;

import main.items.Destination;
import main.items.DestinationTreeSet;
import org.junit.Test;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Class Name: DestinationTreeSetTest.java
 *
 * Description: This class includes JUnit tests for the class DestinationTreeSet.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class DestinationTreeSetTest {

    private String trueDestinationName = "George Street"; //Example of correct destination name

    /**
     * This method tries to create a new DestinationTreeSet and push a null Destination in it.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNullDestinationName(){

        TreeSet<Destination> wrongTreeSet = new TreeSet<Destination>();
        DestinationTreeSet dtst = new DestinationTreeSet(wrongTreeSet);
        dtst.add(null);

    }

    /**
     * This method tries to create a new DestinationTreeSet and push a new Destination in it.
     */
    @Test
    public void testTrueCase(){

        TreeSet<Destination> wrongTreeSet = new TreeSet<Destination>();
        DestinationTreeSet dtst = new DestinationTreeSet(wrongTreeSet);
        dtst.add(new Destination(trueDestinationName));

        assertEquals(1, dtst.getDestinations().size());
    }

}
