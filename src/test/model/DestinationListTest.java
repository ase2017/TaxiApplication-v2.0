package test.model;

import main.model.DestinationList;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
/**
 * @author Jules
 */
public class DestinationListTest {


    /**
     * Checks that for 10 utterances the destination name is not null
     */
    @Test
    public void getRandomDestinationName(){

        for (int i = 0; i < 10; i++) {
            assertNotNull(DestinationList.getRandomDestinationName());
        }
    }

}
