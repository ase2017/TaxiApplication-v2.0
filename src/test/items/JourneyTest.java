package test.items;

import main.io.DataFileReader;
import main.items.Journey;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class Name: JourneyTest.java
 *
 * Description: This class includes JUnit tests for the class Journey.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class JourneyTest {

    private int trueDestinationID = 1; //An example of a correct destination ID
    private String trueTaxiRegistrationNumber = "AA111AA"; //An example of a correct registration number
    private int trueNumberOfPassengers = 2; //An example of a correct number of passengers
    private double trueTime = 11; //An example of a correct duration
    private double trueMaximumVelocity = 10; //An example of a correct maximum velocity

    /**
     * This method tries to create a new Journey Object with a null destination ID
     */
    @Test
    public void testWrongDestinationID(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        int wrongDestinationID = 0;

        System.setOut(new PrintStream(serialContent));
        new Journey(wrongDestinationID, trueTaxiRegistrationNumber, trueNumberOfPassengers,
                trueTime, trueMaximumVelocity);

        assertEquals("Error! Invalid record`s ID (in file journeys.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Journey Object using a registration
     * number with wrong length.
     */
    @Test
    public void testWrongLengthRegistrationNumber(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        String wrongRegistrationNumber = "A1A";

        System.setOut(new PrintStream(serialContent));
        new Journey(trueDestinationID, wrongRegistrationNumber, trueNumberOfPassengers,
                trueTime, trueMaximumVelocity);

        assertEquals("Error! Wrong registration number (in file journeys.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Journey Object using a registration
     * number with wrong pattern.
     */
    @Test
    public void testWrongPatternRegistrationNumber(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        String wrongRegistrationNumber = "AA1A";

        System.setOut(new PrintStream(serialContent));
        new Journey(trueDestinationID, wrongRegistrationNumber, trueNumberOfPassengers,
                trueTime, trueMaximumVelocity);

        assertEquals("Error! Wrong registration number (in file journeys.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());

    }

    /**
     * This method tries to create a new Journey Object with a wrong passenger number.
     */
    @Test
    public void testWrongPassengerNumber(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        int wrongNumberOfPassengers = 0;

        System.setOut(new PrintStream(serialContent));
        new Journey(trueDestinationID, trueTaxiRegistrationNumber, wrongNumberOfPassengers,
                trueTime, trueMaximumVelocity);

        assertEquals("Error! The number of passengers is not correct (in file journeys.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Journey Object with a wrong duration.
     */
    @Test
    public void testWrongTime(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        int wrongTime = 0;

        System.setOut(new PrintStream(serialContent));
        new Journey(trueDestinationID, trueTaxiRegistrationNumber, trueNumberOfPassengers,
                wrongTime, trueMaximumVelocity);

        assertEquals("Error! The time of the journey is not in the correct format " +
                        "(in file journeys.txt in line: " + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new Journey Object with a wrong maximum velocity.
     */
    @Test
    public void testWrongMaximumVelocity(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        int wrongMaximumVelocity = -1;

        System.setOut(new PrintStream(serialContent));
        new Journey(trueDestinationID, trueTaxiRegistrationNumber, trueNumberOfPassengers,
                trueTime, wrongMaximumVelocity);

        assertEquals("Error! The maximum velocity is not in the correct range " +
                "(in file journeys.txt in line: " + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tests the creation of a Journey Object.
     */
    @Test
    public void trueCaseExample(){

        assertNotEquals(null, new Journey( trueDestinationID, trueTaxiRegistrationNumber, trueNumberOfPassengers,
                trueTime, trueMaximumVelocity));

    }
}
