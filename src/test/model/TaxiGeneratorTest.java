package test.model;
import main.model.TaxiGenerator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class TaxiGeneratorTest {

    @Test
    public void generateRandomRegistrationNumber() {

        for (int i = 0; i < 20; i++)
            System.out.println(TaxiGenerator.generateRandomRegistrationNumber());
    }
}
