package test.model;

import main.model.Taxi;
import main.model.TaxiList;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * @author Jules
 */
public class TaxiListTest {

    @Test
    public void add(){

        TaxiList taxiList = new TaxiList();

        Taxi nullTaxi = null;
        Taxi taxi = new Taxi("AX565AX",5);
        Taxi taxiSameRegistrationNumber = new Taxi("AX565AX",8);
        Taxi taxiDifferentRegistrationNumber = new Taxi("ZZ565AX",5);

        taxiList.add(nullTaxi);
        assertTrue(taxiList.getTaxis().size() == 0);

        taxiList.add(taxi);
        assertTrue(taxiList.getTaxis().size() == 1);

        taxiList.add(taxiSameRegistrationNumber);
        assertTrue(taxiList.getTaxis().size() == 1);

        taxiList.add(taxiDifferentRegistrationNumber);
        assertTrue(taxiList.getTaxis().size() == 2);

        // if the list is null, handled
        taxiList.setTaxis(null);
        taxiList.add(taxi);
        assertTrue(taxiList.getTaxis().size() == 1);


    }

    @Test
    public void containsTax(){

        TaxiList taxiList = new TaxiList();

        Taxi taxi = new Taxi("AX565AX",5);

        // does not contain before adding
        assertFalse(taxiList.containsTaxi(taxi));

        // contains after adding
        taxiList.add(taxi);
        assertTrue(taxiList.containsTaxi(taxi));

        // contains a taxi with the same registration number
        Taxi taxiSameRegistrationNumber = new Taxi("AX565AX",8);
        assertTrue(taxiList.containsTaxi(taxiSameRegistrationNumber));

        // does not contain this taxi
        Taxi taxiDifferentRegistrationNumber = new Taxi("ZZ565AX",5);
        assertFalse(taxiList.containsTaxi(taxiDifferentRegistrationNumber));

        // if the queue is null, check that it does not crash
        taxiList.setTaxis(null);
        assertFalse(taxiList.containsTaxi(taxi));
    }

}
