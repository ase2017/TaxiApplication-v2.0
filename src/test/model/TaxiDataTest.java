package test.model;

import main.model.Taxi;
import main.model.TaxiData;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jules
 */
public class TaxiDataTest {


    @Test

    public void createTaxiData(){

        TaxiData taxiData = new TaxiData(5,10,10);
        assertTrue(taxiData.getTaxiList().getTaxis().size() == 5);
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 5);

        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 10);
        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 10);
    }

    @Test
    public void generateAndAddTaxi(){
        TaxiData taxiData = new TaxiData();

        // test when adding on a null taxiQueue object
        taxiData.generateAndAddTaxi();
        assertTrue(taxiData.getTaxiList().getTaxis().size() == 1);
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 1);

        // test when adding on a non null taxiQueue object
        taxiData = new TaxiData(5,10,10);
        taxiData.generateAndAddTaxi();
        assertTrue(taxiData.getTaxiList().getTaxis().size() == 6);
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 6);

    }

    @Test
    public void generateAndAddGroup(){

        TaxiData taxiData = new TaxiData();

        // test when adding on a null taxiQueue object
        taxiData.generateAndAddGroup();
        assertNotNull(taxiData.getPassengerQueue());
        assertNotNull(taxiData.getPassengerQueue().getGroupOfPassengersQueue());
        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 1);

        // test when adding on a non null taxiQueue object
        taxiData = new TaxiData(5,10,10);
        taxiData.generateAndAddGroup();
        assertNotNull(taxiData.getPassengerQueue());
        assertNotNull(taxiData.getPassengerQueue().getGroupOfPassengersQueue());
        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 11);
    }


    @Test
    public void addTaxi(){

        TaxiData emptyTaxiData = new TaxiData();

        Taxi taxi = new Taxi("AX555AX",5);
        Taxi taxiWithDifferentRegNumber = new Taxi("ZZ555ZZ",5);
        Taxi taxiWithSameRegNumber = new Taxi("AX555AX",10);

        // test when adding on a null taxiQueue object
        assertTrue(emptyTaxiData.addTaxi(taxi));
        assertTrue(emptyTaxiData.getTaxiList().getTaxis().size() == 1);
        assertTrue(emptyTaxiData.getTaxiQueue().getTaxisQueue().size() == 1);

        // test when adding a taxi with same registration number
        assertFalse(emptyTaxiData.addTaxi(taxiWithSameRegNumber));
        assertTrue(emptyTaxiData.getTaxiList().getTaxis().size() == 1);
        assertTrue(emptyTaxiData.getTaxiQueue().getTaxisQueue().size() == 1);

        // test when adding a taxi with a different registration number
        assertTrue(emptyTaxiData.addTaxi(taxiWithDifferentRegNumber));
        assertTrue(emptyTaxiData.getTaxiList().getTaxis().size() == 2);
        assertTrue(emptyTaxiData.getTaxiQueue().getTaxisQueue().size() == 2);
    }


    @Test
    public void fillTaxiQueue(){
        TaxiData taxiData = new TaxiData();

        // negative parameter
        taxiData.fillTaxiQueue(-1);
        assertNotNull(taxiData.getTaxiQueue());
        assertNotNull(taxiData.getTaxiList());
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 0);
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 0);


        taxiData = new TaxiData();
        // 0 parameter
        taxiData.fillTaxiQueue(0);
        assertNotNull(taxiData.getTaxiQueue());
        assertNotNull(taxiData.getTaxiList());
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 0);
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 0);

        taxiData = new TaxiData();
        // positive parameter
        taxiData.fillTaxiQueue(1);
        assertNotNull(taxiData.getTaxiQueue());
        assertNotNull(taxiData.getTaxiList());
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 1);
        assertTrue(taxiData.getTaxiQueue().getTaxisQueue().size() == 1);


    }


    @Test
    public void fillGroupsQueue(){

        TaxiData taxiData = new TaxiData();

        // negative parameter
        taxiData.fillGroupsQueue(-1);
        assertNotNull(taxiData.getPassengerQueue());
        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 0);


        taxiData = new TaxiData();
        // 0 parameter
        taxiData.fillGroupsQueue(0);
        assertNotNull(taxiData.getPassengerQueue());
        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 0);

        taxiData = new TaxiData();
        // positive parameter
        taxiData.fillGroupsQueue(1);
        assertNotNull(taxiData.getPassengerQueue());
        assertTrue(taxiData.getPassengerQueue().getGroupOfPassengersQueue().size() == 1);

    }
}
