package test.model;

import main.model.GroupOfPassengers;
import main.model.GroupOfPassengersGenerator;
import main.model.TaxiData;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jules
 */
public class GroupsOfPassengersGeneratorTest {


    @Test
    public void generateGroupOfPassengers(){

        GroupOfPassengers groupOfPassengers;

        GroupOfPassengersGenerator groupOfPassengersGenerator = new GroupOfPassengersGenerator(10);

        for (int i = 0; i < 20; i++){
            groupOfPassengers = groupOfPassengersGenerator.generateGroupOfPassengers();
            assertNotNull(groupOfPassengers);
            assertTrue(groupOfPassengers.getNumberOfPassengers() >= groupOfPassengersGenerator.getMinNumberOfPeopleInGroup()
                    && groupOfPassengers.getNumberOfPassengers() <= groupOfPassengersGenerator.getMaxNumberOfPeopleInGroup());

        }
    }

    @Test
    public void generateGroupOfPassengersList(){

        GroupOfPassengersGenerator groupOfPassengersGenerator = new GroupOfPassengersGenerator(10);

        assertNotNull(groupOfPassengersGenerator.generateGroupOfPassengers(-1));
        assertTrue(groupOfPassengersGenerator.generateGroupOfPassengers(-1).size() == 0);

        assertNotNull(groupOfPassengersGenerator.generateGroupOfPassengers(0));
        assertTrue(groupOfPassengersGenerator.generateGroupOfPassengers(0).size() == 0);

        assertNotNull(groupOfPassengersGenerator.generateGroupOfPassengers(1));
        assertTrue(groupOfPassengersGenerator.generateGroupOfPassengers(1).size() == 1);

        assertNotNull(groupOfPassengersGenerator.generateGroupOfPassengers(5));
        assertTrue(groupOfPassengersGenerator.generateGroupOfPassengers(5).size() == 5);

    }



}
