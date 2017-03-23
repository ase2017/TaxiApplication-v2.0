package test.model;

import main.model.Taxi;
import main.model.TaxiQueue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jules
 */
public class TaxiQueueTest {

    @Test
    public void add(){
        TaxiQueue taxiQueue = new TaxiQueue();

        Taxi nullTaxi = null;
        Taxi taxi = new Taxi("AX565AX",5);
        Taxi taxiSameRegistrationNumber = new Taxi("AX565AX",8);
        Taxi taxiDifferentRegistrationNumber = new Taxi("ZZ565AX",5);

        taxiQueue.add(nullTaxi);
        assertTrue(taxiQueue.getTaxisQueue().size() == 0);

        taxiQueue.add(taxi);
        assertTrue(taxiQueue.getTaxisQueue().size() == 1);

        taxiQueue.add(taxiSameRegistrationNumber);
        assertTrue(taxiQueue.getTaxisQueue().size() == 1);

        taxiQueue.add(taxiDifferentRegistrationNumber);
        assertTrue(taxiQueue.getTaxisQueue().size() == 2);
    }

    @Test
    public void popTaxi(){
        TaxiQueue taxiQueue = new TaxiQueue();

        Taxi taxi = new Taxi("AX565AX",5);

        taxiQueue.add(taxi);
        assertTrue(taxiQueue.getTaxisQueue().size() == 1);
        assertNotNull(taxiQueue.popTaxi());

        // if the queue is null, check that it does not crash
        taxiQueue.setTaxisQueue(null);
        assertNull(taxiQueue.popTaxi());
    }

    @Test
    public void containsTaxi(){

        TaxiQueue taxiQueue = new TaxiQueue();

        Taxi taxi = new Taxi("AX565AX",5);

        // does not contain before adding
        assertFalse(taxiQueue.containsTaxi(taxi));

        // contains after adding
        taxiQueue.add(taxi);
        assertTrue(taxiQueue.containsTaxi(taxi));

        // contains a taxi with the same registration number
        Taxi taxiSameRegistrationNumber = new Taxi("AX565AX",8);
        assertTrue(taxiQueue.containsTaxi(taxiSameRegistrationNumber));

        // does not contain this taxi
        Taxi taxiDifferentRegistrationNumber = new Taxi("ZZ565AX",5);
        assertFalse(taxiQueue.containsTaxi(taxiDifferentRegistrationNumber));

        // if the queue is null, check that it does not crash
        taxiQueue.setTaxisQueue(null);
        assertFalse(taxiQueue.containsTaxi(taxi));
    }
}
