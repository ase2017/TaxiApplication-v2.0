package test.items;

import main.io.DataFileReader;
import main.items.Destination;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class Name: DestinationTest.java
 *
 * Description: This class includes JUnit tests for the class Destination.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class DestinationTest {

    private int trueDestinationID = 1; //Example of correct destination id
    private String trueDestinationName = "George Street"; //Example of correct destination name
    private double trueDistance = 22.1; //Example of correct distance
    private boolean trueUrban = true; //Example of correct urban identifier

    /**
     * This method tries to create a new Destination Object with wrong destination id.
     */
    @Test
    public void testWrongDestinationID(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        int wrongDestinationID = 0;

        System.setOut(new PrintStream(serialContent));
        new Destination(wrongDestinationID, trueDestinationName, trueDistance, trueUrban);

        assertEquals("Error! Invalid record`s ID (in file destinations_2017.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());

    }

    /**
     * This method tries to create a new Destination Object with a wrong destination name.
     */
    @Test
    public void testWrongLengthDestinationName2017(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        String wrongDestinationName = "Ab"; //Just checks if destination`s name is between 3 and 30 characters, other check will be implemented in DataFileReaderTest

        System.setOut(new PrintStream(serialContent));
        new Destination(trueDestinationID, wrongDestinationName, trueDistance, trueUrban);

        assertEquals("Error! The name of the destination is invalid (in file destinations_2017.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Destination Object with a null destination name
     */
    @Test
    public void testNullDestinationName2017(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        String wrongDestinationName = null; //Just checks if driver`s name is between 3 and 30 characters, other check will be implemented in DataFileReaderTest

        System.setOut(new PrintStream(serialContent));
        new Destination(trueDestinationID, wrongDestinationName, trueDistance, trueUrban);

        assertEquals("Error! The name of the destination is invalid (in file destinations_2017.txt in line: "
                        + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Destination Object with a wrong distance
     */
    @Test
    public void testWrongRangeDistance(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        int wrongDistance = -1; //Just checks if driver`s name is between 3 and 30 characters, other check will be implemented in DataFileReaderTest

        System.setOut(new PrintStream(serialContent));
        new Destination(trueDestinationID, trueDestinationName, wrongDistance, trueUrban);

        assertEquals("Error! The distance is not in the correct range (in file destinations_2017.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());

    }

    /**
     * This method tries to create a new Destination Object with a destination name of wrong length.
     */
    @Test
    public void testWrongLengthDestinationName2016(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        String wrongDestinationName = "Ab"; //Just checks if destination`s name is between 3 and 30 characters, other check will be implemented in DataFileReaderTest

        System.setOut(new PrintStream(serialContent));
        new Destination(wrongDestinationName);

        assertEquals("Error! The name of the destination is invalid (in file destinations_2016.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Destination 2016 Object with a null destination name.
     */
    @Test
    public void testNullDestinationName2016(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        String wrongDestinationName = null; //Just checks if driver`s name is between 3 and 30 characters, other check will be implemented in DataFileReaderTest

        System.setOut(new PrintStream(serialContent));
        new Destination(wrongDestinationName);

        assertEquals("Error! The name of the destination is invalid (in file destinations_2016.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tests the creation of a Destination 2017 Object.
     */
    @Test
    public void testTrueCase2017(){

        assertNotEquals(null, new Destination( trueDestinationID, trueDestinationName,
                trueDistance, trueUrban));
    }

    /**
     * This method tests the creation of a Destination 2016 Object.
     */
    @Test
    public void testTrueCase2016(){

        assertNotEquals(null, new Destination( trueDestinationName));
    }

}
