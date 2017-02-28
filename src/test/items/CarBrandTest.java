package test.items;

import main.items.CarBrand;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that tests CarBrand methods :
 *
 *      equalsName
 *      containsBrand
 *      toString
 *
 * @author Jules
 */
public class CarBrandTest {

    /**
     * Tests if equalsName method of CarBrand works properly
     */
    @Test
    public void equalsNameTest() {

        // Accepted brand names
        assertTrue(CarBrand.MERCEDES.equalsName("Mercedes"));
        assertTrue(CarBrand.TOYOTA.equalsName("Toyota"));
        assertTrue(CarBrand.NISSAN.equalsName("Nissan"));
        assertTrue(CarBrand.AUDI.equalsName("Audi"));
        assertTrue(CarBrand.BENTLEY.equalsName("Bentley"));

        // wrong lower / upper cases combinations
        assertFalse(CarBrand.MERCEDES.equalsName("mercedes"));
        assertFalse(CarBrand.MERCEDES.equalsName("MERCEDES"));

        // empty String
        assertFalse(CarBrand.MERCEDES.equalsName(""));

        // null String
        assertFalse(CarBrand.MERCEDES.equalsName(null));

        // brand not accepted
        assertFalse(CarBrand.MERCEDES.equalsName("bmw"));

    }

    /**
     * Tests if containsBrand method of CarBrand works properly
     */
    @Test
    public void containsBrandTest() {

        // Accepted brand names
        assertTrue(CarBrand.containsBrand("Mercedes"));
        assertTrue(CarBrand.containsBrand("Toyota"));
        assertTrue(CarBrand.containsBrand("Nissan"));
        assertTrue(CarBrand.containsBrand("Audi"));
        assertTrue(CarBrand.containsBrand("Bentley"));

        // wrong lower / upper cases combinations
        assertFalse(CarBrand.containsBrand("mercedes"));
        assertFalse(CarBrand.containsBrand("MERCEDES"));

        // empty String
        assertFalse(CarBrand.containsBrand(""));

        // null String
        assertFalse(CarBrand.containsBrand(null));

        // brand not accepted
        assertFalse(CarBrand.containsBrand("bmw"));

    }

    /**
     * Tests if toString method of CarBrand works properly
     */
    @Test
    public void toStringTest(){

        assertEquals(CarBrand.MERCEDES.toString(),("Mercedes"));
        assertEquals(CarBrand.TOYOTA.toString(),("Toyota"));
        assertEquals(CarBrand.NISSAN.toString(),("Nissan"));
        assertEquals(CarBrand.AUDI.toString(),("Audi"));
        assertEquals(CarBrand.BENTLEY.toString(),("Bentley"));
    }
}
