package test.items;

import main.io.DataFileReader;
import main.items.Taxi;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Class Name: TaxiTest.java
 *
 * Description: This class includes JUnit tests for the class Taxi.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


public class TaxiTest {
	
	private String trueRegistrationNumber = "AA123AB"; //An example of a correct registration number
	private String trueDriverName = "John Bend"; //An example of a correct driver's name
	private String trueBrand = "Toyota"; //An example of a correct brand name

	/**
	 * This method tries to create a new Taxi Object using a registration number with wrong length.
	 */
	@Test
	public void testWrongLengthRegistrationNumber(){
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongRegistrationNumber = "A1A";
		
		System.setOut(new PrintStream(serialContent));
		new Taxi(wrongRegistrationNumber, trueDriverName, trueBrand);
		
		assertEquals("Error! Wrong registration number (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());
	}

	/**
	 * This method tries to create a new Taxi Object using a registration number with wrong pattern.
	 */
	@Test
	public void testWrongPatternRegistrationNumber(){
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongRegistrationNumber = "AA1A";

		System.setOut(new PrintStream(serialContent));
		new Taxi(wrongRegistrationNumber, trueDriverName, trueBrand);
		
		assertEquals("Error! Wrong registration number (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());
		
	}

	/**
	 * This method tries to create a new Taxi Object using a driver name with wrong length.
	 */
	@Test
	public void testWrongLengthDriverName(){
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongDriverName = "Ja"; //Just checks if driver`s name is between 5 and 30 characters, other check will be implemented in DataFileReaderTest
		
		System.setOut(new PrintStream(serialContent));
		new Taxi(trueRegistrationNumber, wrongDriverName, trueBrand);
		
		assertEquals("Error! Wrong driver`s name (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());
	}

	/**
	 * This method tries to create a new Taxi Object using a driver name with wrong number of words.
	 */
	@Test
	public void testWrongNumberOfWordsDriverName(){

		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongDriverName = "Kadasdsadasdsda"; //If driver`s name less than two words, this should show an error

		System.setOut(new PrintStream(serialContent));
		new Taxi(trueRegistrationNumber, wrongDriverName, trueBrand);

		assertEquals("Error! Wrong driver`s name (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());

	}

	/**
	 * This method tries to create a new Taxi Object using a null driver's name.
	 */
	@Test
	public void testNullDriverName(){
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongDriverName = null; //Just checks if driver`s name is between 3 and 30 characters, other check will be implemented in DataFileReaderTest
		
		System.setOut(new PrintStream(serialContent));
		new Taxi(trueRegistrationNumber, wrongDriverName, trueBrand);
		
		assertEquals("Error! Wrong driver`s name (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());
	}

	/**
	 * This method tries to create a new Taxi Object using a null brand name.
	 */
	@Test
	public void testNullBrandName(){
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongBrandName = null;
		
		System.setOut((new PrintStream(serialContent)));
		new Taxi(trueRegistrationNumber, trueDriverName, wrongBrandName);
		
		assertEquals("Error! Wrong car`s brand (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());
	}

	/**
	 * This method tries to create a new Taxi Object using a brand name with wrong length.
	 */
	@Test
	public void testWrongLengthBrandName(){
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		String wrongBrandName = "J";
		
		System.setOut(new PrintStream(serialContent));
		new Taxi(trueRegistrationNumber, trueDriverName, wrongBrandName);
		
		assertEquals("Error! Wrong car`s brand (in file taxis.txt in line: "
				+ DataFileReader.line_counter + " ).\r\n", serialContent.toString());
	}

	/**
	 * This method tests the creation of a Taxi Object.
	 */
	@Test
	public void testCorrectExample(){

		assertNotEquals(null, new Taxi( trueRegistrationNumber, trueDriverName, trueBrand));

	}
}
