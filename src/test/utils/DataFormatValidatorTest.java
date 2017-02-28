package test.utils;

import main.utils.DataFormatValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataFormatValidatorTest {


	@Test
	public void validateRegistrationNumber () {

		String regNumberLegal = "AA743HE";
		String regNumberEmpty = "";
		String regNumberNull = null;
		String regNumberIllegal = "AAAAAAA";
		String regNumberShortString = "123";
		String regNumberLongString = "12345678910";


		assertTrue(DataFormatValidator.validateRegistrationNumber(regNumberLegal) );

		assertFalse(DataFormatValidator.validateRegistrationNumber(regNumberIllegal) );

		assertFalse(DataFormatValidator.validateRegistrationNumber(regNumberNull) );

		assertFalse(DataFormatValidator.validateRegistrationNumber(regNumberEmpty) );

		assertFalse(DataFormatValidator.validateRegistrationNumber(regNumberShortString) );

		assertFalse(DataFormatValidator.validateRegistrationNumber(regNumberLongString) );

	}


	@Test
	public void validateDriverName() {


		String driverNameLegal = "Peter Parker";
		String driverNameEmptyName = "";
		String driverNameNull = null;
		String tooShortName = "Peter";
		String goodLengthName = "Parker";
		String driverNameMiddleName = "Peter D. Parker";
		String tooLongName = "A new taxi driver's name with length more than 30 characters!";


		// good format
		assertTrue(DataFormatValidator.validateDriverName(driverNameLegal));

		// too short
		assertFalse(DataFormatValidator.validateDriverName(tooShortName) );

		// good length but there are not at least 2 words (no space)
		assertFalse(DataFormatValidator.validateDriverName(goodLengthName) );

		// with middlename
		assertTrue(DataFormatValidator.validateDriverName(driverNameMiddleName) );

		// empty
		assertFalse(DataFormatValidator.validateDriverName(driverNameEmptyName) );

		// too long
		assertFalse(DataFormatValidator.validateDriverName(tooLongName) );

		// null
		assertFalse(DataFormatValidator.validateDriverName(driverNameNull) );


	}


	@Test
	public void validateBrand() {

		String brandNameLegal = "Mercedes";
		String brandNameEmpty = "";
		String brandNameNull = null;
		String brandNameShortString = "X";
		String brandNameLongString = "A brand name with more than 15 characters!";


		assertTrue(DataFormatValidator.validateBrand(brandNameLegal) );

		assertFalse(DataFormatValidator.validateBrand(brandNameEmpty) );

		assertFalse(DataFormatValidator.validateBrand(brandNameNull) );

		assertFalse(DataFormatValidator.validateBrand(brandNameShortString) );

		assertFalse(DataFormatValidator.validateBrand(brandNameLongString) );



	}


	@Test
	public void validateDestinationName() {

		String destinationLegal = "Heriot-Watt";
		String destinationEmpty = "";
		String destinationNull = null;
		String brandNameShortString = "X";
		String brandNameLongString = "This is a destination name with more tha 30 characters!";

		assertTrue(DataFormatValidator.validateDestinationName(destinationLegal));

		assertFalse(DataFormatValidator.validateDestinationName(destinationEmpty));

		assertFalse(DataFormatValidator.validateDestinationName(destinationNull));

		assertFalse(DataFormatValidator.validateDestinationName(brandNameShortString));

		assertFalse(DataFormatValidator.validateDestinationName(brandNameLongString));


	}


	@Test
	public void validateDistance () {

		double distanceLegal = 13.0;
		double distanceZero = 0.0;
		double distanceNegative = -10.0;
		double distanceTooLong = 1000.0;


		assertTrue(DataFormatValidator.validateDistance(distanceLegal));

		assertFalse(DataFormatValidator.validateDistance(distanceZero));

		assertFalse(DataFormatValidator.validateDistance(distanceNegative));

		assertFalse(DataFormatValidator.validateDistance(distanceTooLong));


	}


	@Test
	public void validatePassengerNumber() {

		int numOfPassengersLegal = 2;
		int numOfPassengersZero = 0;
		int numOfPassengersNegative = -3;
		int numOfPassengersTooMany = 100;


		assertTrue(DataFormatValidator.validatePassengerNumber(numOfPassengersLegal));

		assertFalse(DataFormatValidator.validatePassengerNumber(numOfPassengersZero));

		assertFalse(DataFormatValidator.validatePassengerNumber(numOfPassengersNegative));

		assertFalse(DataFormatValidator.validatePassengerNumber(numOfPassengersTooMany));

	}


	@Test
	public void validateUrban() {

		String urbanLegal = "Y";
		String urbanLegal2 = "N";
		String urbanEmpty = "";
		String urbanIllegal = "Not Legal Urban string!";
		String urbanNull = null;


		assertTrue(DataFormatValidator.validateUrban(urbanLegal));

		assertTrue(DataFormatValidator.validateUrban(urbanLegal2));

		assertFalse(DataFormatValidator.validateUrban(urbanEmpty));

		assertFalse(DataFormatValidator.validateUrban(urbanIllegal));

		assertFalse(DataFormatValidator.validateUrban(urbanNull));


	}

	@Test
	public void validateTime(){

		double negativeTime = -1.0;
		double nullTime = 0;
		double positiveTime = 5;

		assertFalse(DataFormatValidator.validateTime(negativeTime));
		assertFalse(DataFormatValidator.validateTime(nullTime));
		assertTrue(DataFormatValidator.validateTime(positiveTime));
	}


	@Test
	public void validateMaximumVelocity(){

		double negativeMaximumVelocity = -1.0;
		double nullMaximumVelocity = 0;
		double positiveMaximumVelocity = 5;
		double tooFastMaximumVelocity = 150;


		assertFalse(DataFormatValidator.validateMaximumVelocity(negativeMaximumVelocity));
		assertTrue(DataFormatValidator.validateMaximumVelocity(nullMaximumVelocity));
		assertTrue(DataFormatValidator.validateMaximumVelocity(positiveMaximumVelocity));
		assertFalse(DataFormatValidator.validateMaximumVelocity(tooFastMaximumVelocity));

	}


	@Test
	public void validateDestinationID() {

		int idLegal = 400;
		int idZero = 0;
		int idNegative = -3;

		assertTrue(DataFormatValidator.validateDestinationID(idLegal));

		assertFalse(DataFormatValidator.validateDestinationID(idZero));

		assertFalse(DataFormatValidator.validateDestinationID(idNegative));

	}


}