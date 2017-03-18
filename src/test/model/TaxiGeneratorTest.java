package test.model;
import main.model.Taxi;
import main.model.TaxiGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jules
 */
public class TaxiGeneratorTest {



    @Test
    public void generateTaxi(){
        Taxi taxi;
        for (int i = 0; i < 20; i++){
            taxi = TaxiGenerator.generateTaxi();
            assertNotNull(taxi);
            assertTrue(taxi.getMaximumNumberOfPassengers() >= TaxiGenerator.MINIMUM_NUMBER_OF_PASSENGERS
                && taxi.getMaximumNumberOfPassengers() <= TaxiGenerator.MAXIMUM_NUMBER_OF_PASSENGERS);
        }
    }


    @Test
    public void generateTaxiList(){
        assertNotNull(TaxiGenerator.generateTaxi(-1));
        assertTrue(TaxiGenerator.generateTaxi(-1).size() == 0);

        assertNotNull(TaxiGenerator.generateTaxi(0));
        assertTrue(TaxiGenerator.generateTaxi(0).size() == 0);

        assertNotNull(TaxiGenerator.generateTaxi(1));
        assertTrue(TaxiGenerator.generateTaxi(1).size() == 1);

        assertNotNull(TaxiGenerator.generateTaxi(5));
        assertTrue(TaxiGenerator.generateTaxi(5).size() == 5);
    }


    @Test
    public void generateRandomRegistrationNumber() {

        for (int i = 0; i < 20; i++)
            assertTrue(TaxiGenerator.generateRandomRegistrationNumber().matches("^[0-9A-Z]{7}$"));

    }

    @Test
    public void generateMaximumNumberOfPassengers(){

    for (int i = 0; i < 20; i++){

        assertTrue(TaxiGenerator.generateMaximumNumberOfPassengers() >= TaxiGenerator.MINIMUM_NUMBER_OF_PASSENGERS
                &&  TaxiGenerator.generateMaximumNumberOfPassengers() <= TaxiGenerator.MAXIMUM_NUMBER_OF_PASSENGERS);

        }
    }

}
