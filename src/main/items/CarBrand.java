package main.items;

/**
 * Class that contains the list of accepted car brands
 * Methods to check if a String belongs to the list of accepted car brands
 *
 * @author Jules
 */
public enum CarBrand {

    MERCEDES ("Mercedes"),
    TOYOTA ("Toyota"),
    NISSAN ("Nissan"),
    AUDI ("Audi"),
    BENTLEY("Bentley");

    private final String carBrand;

    private CarBrand(String s) {
        carBrand = s;
    }

    /**
     * Checks if the current CarBrand has the same name as "brand", the
     * given brand.
     * @param brand : the brand (String)
     * @return true if the name is the same, false otherwise
     */
    public boolean equalsName(String brand) {
        return carBrand.equals(brand);
    }


    /**
     * Checks if the enum contains the brand
     * @param brand the brand (String)
     * @return true if the brand is in the Enum, false otherwise
     */
    public static boolean containsBrand(String brand) {
        for (CarBrand cb : CarBrand.values()) {
            if(cb.equalsName(brand))
                return true;
        }
        return false;
    }

    /**
     * Returns the associated String of the current enum
     * @return the associated String of the current enum
     */
    public String toString() {
        return this.carBrand;
    }
}