package test.items;

import main.io.DataFileReader;
import main.items.Taxi;
import main.items.TaxiTreeMap;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

/**
 * Class Name: TaxiTreeMapTest.java
 *
 * Description: This class includes JUnit tests for the class TaxiTreeMap.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


public class TaxiTreeMapTest {

    private String trueRegistrationNumber = "AA111AA"; //Example of correct registration number
    private String trueDriverName = "Jack Ko"; //Example of correct driver's name
    private String trueBrandName = "Toyota"; //Example of correct brand name

    /**
     * This method tries to push in the TreeMap a null Taxi Object.
     */
    @Test(expected = NullPointerException.class)
    public void testAddNullTaxi(){

        TreeMap<String, Taxi> taxis = new TreeMap<String, Taxi>();
        Taxi tx = null;
        TaxiTreeMap txtmp = new TaxiTreeMap(taxis);
        txtmp.addTaxi(tx);

    }

    /**
     * This method tries to push in the TreeMap two Taxi Objects with the same ID.
     */
    @Test
    public void testDuplicateID(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(serialContent));

        TreeMap<String, Taxi> taxis = new TreeMap<String, Taxi>();
        TaxiTreeMap txtmp = new TaxiTreeMap(taxis);
        Taxi tx1 = new Taxi(trueRegistrationNumber, trueDriverName, trueBrandName);
        Taxi tx2 = new Taxi(trueRegistrationNumber, trueDriverName, trueBrandName);

        txtmp.addTaxi(tx1);
        txtmp.addTaxi(tx2);

        assertEquals("Error! Duplicate item ID (in file taxis.txt in line: "
                + DataFileReader.line_counter + " ).\r\n", serialContent.toString());
    }

    /**
     * This method tries to create a new TaxiTreeMap and pushes a new Taxi in it.
     */
    @Test
    public void testTrueCase(){

        final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(serialContent));

        TreeMap<String, Taxi> taxis = new TreeMap<String, Taxi>();
        TaxiTreeMap txtmp = new TaxiTreeMap(taxis);
        Taxi tx1 = new Taxi(trueRegistrationNumber, trueDriverName, trueBrandName);

        txtmp.addTaxi(tx1);

        assertEquals(1, txtmp.getTaxis().size());
    }
}
