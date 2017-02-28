package main;

import main.io.DataFileReader;
import main.io.DataFileWriter;
import main.items.TaxiData;
import main.view.MenuDisplayer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class Name: TaxiService.java
 * 
 * Description: This class is initializing the main menu and starts two
 * 				core methods which are responsible for reading from
 * 				the input files and writing to the output files.
 * 
 * @author George Goniotakis
 * @since Feb 9, 2017
 */

public class TaxiService {

	public TaxiData taxidata; //Create a new variable of type TaxiData
	public static int TOTAL_TRIES_BEFORE_EXIT = 3; //Exit application after three wrong menu choices

	/**
	 * This method initializes a new TaxiData Object and calls
	 * the method that shows the main menu.
	 * 
	 */
	public void start() {

		taxidata = new TaxiData(); //Initialize a new TaxiData Object
		this.mainMenu(); //Show the main menu
	}

	/**
	 * This method calls the functions that read the data from the
	 * text files and pass them to a TaxiData Object.
	 * 
	 * @return boolean
	 */
	private boolean readFiles() {

		System.out.print("\n"); //Output a new line
		DataFileReader fr = new DataFileReader(); //Initialize a new object that will read the text files

		taxidata.setTaxis(fr.loadTaxis()); //Read the content of taxis.txt and pass it to taxidata
		taxidata.setJourneys(fr.loadJourney()); //Read the content of journeys.txt and pass it to taxidata
		taxidata.setCurrentYearDestinations(fr.loadDestinations2017()); //Read the content of destinations_2017.txt
		taxidata.setPreviousYearDestinations(fr.loadDestinations2016()); //Read the content of destinations_2016.txt
	
		// If all data structures have at least one record each
		if (taxidata.checkAllCollectionsAreInitialized()) {
			return true;
		} else {
			exit("\t \n Not enough records found in one of the input files."); //Exit application with this message
		}

		return false;
	}
	
	/**
	 * This method is creating the main menu.
	 * 
	 * @exception InputMismatchException 
	 */
	private void mainMenu() {

		Scanner scan = new Scanner(System.in); //Initialize a scanner to get the menu selection by the user
		MenuDisplayer menu = new MenuDisplayer(); //Show a menu header
		
		int choice = 0; //Variable to save the user`s choice
		int error_counter = 0; //Variable to save the user`s invalid actions
		
		while (error_counter != TOTAL_TRIES_BEFORE_EXIT) {

			menu.displayMainMenu(); //Display menu header
			System.out.print("\n\t Type your choice: ");

			try {
				
				choice = scan.nextInt(); //Get the input from System.in
				
				switch (choice) {		

				case 1:
					
					error_counter = 0;
					if (readFiles()) {
						writeFiles();
					} else {
						exit("\nError! No files found.");
					}
					break;

				case 2:
					exit("\t See you later!\n");
					scan.close();
					break;

				default:
					error_counter++;
					System.out.println("\t \nInvalid argument! Your choice has to be a number between 1-2.");
					break;
					
				}
			} catch (InputMismatchException e) {
				scan.nextLine();
				error_counter++;
				System.out.println("\t \nInvalid argument! Your choice has to be a number between 1-2.");
			}		
		}
	}

	/**
	 * This method terminates the application by showing an
	 * error message.
	 * 
	 * 
	 * @param message The error message
	 */
	private void exit(String message) {

		System.out.println(message); //Output the error message
		System.exit(0); //Exit application

	}

	/**
	 * This method is initializing a new DataFileWriter Object
	 * which is responsible for outputting the reports.
	 */
	private void writeFiles() {

		DataFileWriter df = new DataFileWriter();
		df.writeFiles(taxidata); //Pass the input data to the method that writes the files

	}
}