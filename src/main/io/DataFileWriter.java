package main.io;

import main.items.TaxiData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * DataFileWriter is a public class that writes the computation result
 * to three output files top-5.txt, driver-Journeys.txt
 * and visited-Places.txt
 *
 * @author Chiotis
 *
 */
public class DataFileWriter {

	public static final String OUTPUT_DIRECTORY_NAME = "outputFiles";
	public static final String FILE_NAME_TOP_5 = "top-" + TaxiData.topNJourneys + ".txt";
	public static final String FILE_NAME_PLACES_PER_DRIVER = "driver-Journeys.txt";
	public static final String FILE_NAME_PLACES = "visited-Places.txt";

	/**
	 * writeFiles is a public method that takes the appropriate Stings
	 * from TaxiData, call the three methods that creates the output files
	 * and finally checks if the process ended successfully.
	 *
	 * @param taxidata contains the Strings that we will write to the output files.
	 *
	 * @throws NullPointerException In case that some file pointer is null
	 */
	public void writeFiles(TaxiData taxidata) {



		// Call the three methods in order to create the three output files.
		if(taxidata != null) {

			boolean file1_OK = false;
			boolean file2_OK = false;
			boolean file3_OK = false;

			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println("\nCreating output files...\n");

			// Creates the output Directory, if does not exists.
			try{
				File f = new File(OUTPUT_DIRECTORY_NAME);
				if (!f.isDirectory()) {
					f.mkdir();
				}
			} catch (Exception e){
				System.out.print(e.getMessage());
			}

			file1_OK = writeFile1(taxidata);
			file2_OK = writeFile2(taxidata);
			file3_OK = writeFile3(taxidata);

			// Inform user whether the process was successful or not.
			if ( file1_OK )
				System.out.println("\t+ <" + FILE_NAME_TOP_5 + "> successfully created!");
			else
				System.out.println("\t->Error occurred in " + FILE_NAME_TOP_5 + "...");

			if ( file2_OK )
				System.out.println("\t+ <" + FILE_NAME_PLACES_PER_DRIVER + "> successfully created!");
			else
				System.out.println("\t->Error occurred in " + FILE_NAME_PLACES_PER_DRIVER + "...");

			if ( file3_OK )
				System.out.println("\t+ <" + FILE_NAME_PLACES + "> successfully created!");
			else
				System.out.println("\t->Error occurred in " + FILE_NAME_PLACES + "...");


			if ( file1_OK && file2_OK && file3_OK )
				System.out.println("\nProcess completed!!!");
			else
				System.out.println("\nProcess ended with errors...");


			System.out.println("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		} else {
			throw new NullPointerException("TaxiData is null!");
		}
	}


	/**
	 * writeFile1 creates the top-5 output file if does not already exists
	 * and write the resulted string from TaxiData.
	 *
	 * @param taxidata contains the String needed for the top-5 journey's output file.
	 *
	 * @throws IOException If the file cannot be created/written
	 * @throws NullPointerException In case that the file pointer is null
	 * @throws SecurityException In case that file cannot be created
	 *
	 * @return true if process ended successfully else return false.
	 *
	 */
	public boolean writeFile1(TaxiData taxidata){

		if(taxidata == null) {
			throw new NullPointerException("TaxiData is null!");
		} else {

			File f = new File(OUTPUT_DIRECTORY_NAME);
			if (!f.isDirectory()) {
				f.mkdir();
			}

			BufferedWriter buffWriterFile1 = null;
			FileWriter fileWriterFile1 = null;

			try {

				String fileContents = "";

				// Open the FILE_NAME_TOP_5 (instance variable) file.
				fileWriterFile1 = new FileWriter( OUTPUT_DIRECTORY_NAME + "/" + FILE_NAME_TOP_5 );

				if(fileWriterFile1 != null)
					buffWriterFile1 = new BufferedWriter(fileWriterFile1);


				fileContents = taxidata.formatJourneyFile();	// Takes the resulted data from the Taxi's Data object.

				if(buffWriterFile1 != null && fileContents != null)
					buffWriterFile1.write(fileContents);			// Writes the data to the file


			} catch (IOException | NullPointerException | SecurityException e) {

				System.out.println( "\t +In file: " + FILE_NAME_TOP_5 + " failed to write content. [" + e.getMessage() + "]." );
				return false;

			} finally {

				try {

					// Close the file descriptors.

					if (buffWriterFile1 != null)
						buffWriterFile1.close();

				} catch (IOException e) {

					System.out.println( "\t +File: " + FILE_NAME_TOP_5 + " failed to close. [" + e.getMessage() + "]." );
					return false;

				}

			}
		}

		return true;
	}


	/**
	 * writeFile2 creates the driver's Journey output file if does not already exists
	 * and write the resulted string from TaxiData.
	 *
	 * @param taxidata contains the String needed for the top-5 driver's Journey output file.
	 *
	 * @throws IOException If the file cannot be created/written
	 * @throws NullPointerException In case that the file pointer is null
	 * @throws SecurityException In case that file cannot be created
	 *
	 * @return true if process ended successfully
	 * 	       else return false.
	 */
	public boolean writeFile2(TaxiData taxidata) {


		if(taxidata == null) {
			throw new NullPointerException("TaxiData is null!");
		} else {

			File f = new File(OUTPUT_DIRECTORY_NAME);
			if (!f.isDirectory()) {
				f.mkdir();
			}

			BufferedWriter buffWriterFile2 = null;
			FileWriter fileWriterFile2 = null;

			try {

				String fileContents = "";

				// Open the FILE_NAME_PLACES_PER_DRIVER (instance variable) file.
				fileWriterFile2 = new FileWriter(OUTPUT_DIRECTORY_NAME + "/" + FILE_NAME_PLACES_PER_DRIVER);

				if(fileWriterFile2 != null)
					buffWriterFile2 = new BufferedWriter(fileWriterFile2);


				fileContents = taxidata.formatPlacesVisitedPerDriver();        // Takes the resulted data from the Taxi's Data object.

				if(buffWriterFile2 != null && fileContents != null)
					buffWriterFile2.write(fileContents);                        // Writes the data to the file.


			} catch (IOException | NullPointerException | SecurityException e) {

				System.out.println("\t +In file: " + FILE_NAME_PLACES_PER_DRIVER + " failed to write content. [" + e.getMessage() + "].");
				return false;

			} finally {

				try {

					// Close the file descriptors.
					if (buffWriterFile2 != null)
						buffWriterFile2.close();

				} catch (IOException e) {

					System.out.println("\t +File: " + FILE_NAME_PLACES_PER_DRIVER + " failed to close. [" + e.getMessage() + "].");
					return false;

				}

			}
		}

		return true;
	}


	/**
	 * writeFile3 creates the visited Places output file if does not already exists
	 * and write the resulted string from TaxiData.
	 *
	 * @param taxidata contains the String needed for the visited Places by taxis output file.
	 *
	 * @throws IOException If the file cannot be created/written
	 * @throws NullPointerException In case that the file pointer is null
	 * @throws SecurityException In case that file cannot be created
	 *
	 * @return true if process ended successfully
	 * 	       else return false.
	 */
	public boolean writeFile3(TaxiData taxidata) {


		if(taxidata == null) {
			throw new NullPointerException("TaxiData is null!");
		} else {

			BufferedWriter buffWriterFile3 = null;
			FileWriter fileWriterFile3 = null;

			try {

				File f = new File(OUTPUT_DIRECTORY_NAME);
				if (!f.isDirectory()) {
					f.mkdir();
				}

				String fileContents = "";

				fileWriterFile3 = new FileWriter(OUTPUT_DIRECTORY_NAME + "/" + FILE_NAME_PLACES);

				if(fileWriterFile3 != null)
					buffWriterFile3 = new BufferedWriter(fileWriterFile3);


				fileContents = taxidata.formatPlacesVisited();        // Takes the resulted data from the Taxi's Data object.

				if(buffWriterFile3 != null && fileContents != null)
					buffWriterFile3.write(fileContents);                // Writes the data to the file.


			} catch (IOException | NullPointerException | SecurityException e) {

				System.out.println("\t +In file: " + FILE_NAME_PLACES + " failed to write content. [" + e.getMessage() + "].");
				return false;

			} finally {

				try {

					// Close the file descriptors.
					if (buffWriterFile3 != null)
						buffWriterFile3.close();

				} catch (IOException e) {

					System.out.println("\t +File: " + FILE_NAME_PLACES + " failed to open. [" + e.getMessage() + "].");
					return false;

				}

			}
		}

		return true;
	}
}