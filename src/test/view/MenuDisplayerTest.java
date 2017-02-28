package test.view;

import main.view.MenuDisplayer;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Class Name: MenuDisplayerTest.java
 *
 * Description: This class includes JUnit tests for the class MenuDisplayer.
 *
 * @author George Goniotakis
 * @since Feb 9, 2017
 */


public class MenuDisplayerTest {

	/**
	 * This method test if the main menu is shown properly.
	 */
	@Test
	public void showMenuTest(){
		
		String expectedMenuContent = "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n\r\n\t 1."
				+ " Create output files.\r\n\t 2. Exit.\n\r\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ "%%%%%%%%%%%\n\r\n";
		
		final ByteArrayOutputStream serialContent = new ByteArrayOutputStream();
		
		System.setOut(new PrintStream(serialContent));
		MenuDisplayer md = new MenuDisplayer();
		md.displayMainMenu();
		
		assertEquals(expectedMenuContent, serialContent.toString());
	}
}
