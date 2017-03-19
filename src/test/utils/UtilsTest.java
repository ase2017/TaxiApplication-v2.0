package test.utils;

import main.utils.Utils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Jules
 */
public class UtilsTest {

    @Test
    public void getIntBetween(){
        for (int i = 0; i < 20; i++){

            assertTrue(Utils.getIntBetween(1,5) >= 1
                    &&  Utils.getIntBetween(1,5) <= 5);

        }
    }
}
