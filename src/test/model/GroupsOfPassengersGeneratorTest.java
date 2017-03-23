package test.model;

import main.model.GroupOfPassengers;
import main.model.GroupOfPassengersGenerator;
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

        for (int i = 0; i < 20; i++){
            groupOfPassengers = GroupOfPassengersGenerator.generateGroupOfPassengers();
            assertNotNull(groupOfPassengers);
            assertTrue(groupOfPassengers.getNumberOfPassengers() >= GroupOfPassengersGenerator.MIN_NUMBER_OF_PEOPLE_IN_GROUP
                    && groupOfPassengers.getNumberOfPassengers() <= GroupOfPassengersGenerator.MAX_NUMBER_OF_PEOPLE_IN_GROUP);

        }
    }

    @Test
    public void generateGroupOfPassengersList(){

        assertNotNull(GroupOfPassengersGenerator.generateGroupOfPassengers(-1));
        assertTrue(GroupOfPassengersGenerator.generateGroupOfPassengers(-1).size() == 0);

        assertNotNull(GroupOfPassengersGenerator.generateGroupOfPassengers(0));
        assertTrue(GroupOfPassengersGenerator.generateGroupOfPassengers(0).size() == 0);

        assertNotNull(GroupOfPassengersGenerator.generateGroupOfPassengers(1));
        assertTrue(GroupOfPassengersGenerator.generateGroupOfPassengers(1).size() == 1);

        assertNotNull(GroupOfPassengersGenerator.generateGroupOfPassengers(5));
        assertTrue(GroupOfPassengersGenerator.generateGroupOfPassengers(5).size() == 5);

    }



}
