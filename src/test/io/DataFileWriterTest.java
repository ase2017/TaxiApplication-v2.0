package test.io;

import main.exceptions.DuplicateIDException;
import main.io.DataFileWriter;
import main.items.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class DataFileWriterTest {

    TaxiData taxiDataTest;
    DataFileWriter fileWriterObject;

    @Before
    public void loadFunc() {

        taxiDataTest = new TaxiData();
        fileWriterObject = new DataFileWriter();

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    /***********************************************************************************************************************
     *
     * DataFileWriter: Open the default files under the default directory and fill them with data.
     *
     ***********************************************************************************************************************/

    /**
     * Tests writing output files when taxiData is null
     * @author Jules
     */
    @Test(expected=NullPointerException.class)
    public void writeFilesTestNullTaxiData() throws NullPointerException{


        try{

            File f = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME);

            if (f.isDirectory()) {
                File fileNamePlaces = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_PLACES);
                File fileNameTopNJourneys = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_TOP_5);
                File filePlacesPerDriver = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_PLACES_PER_DRIVER);
                Files.deleteIfExists(fileNamePlaces.toPath());
                Files.deleteIfExists(fileNameTopNJourneys.toPath());
                Files.deleteIfExists(filePlacesPerDriver.toPath());
            }

        }catch(Exception e){

            e.printStackTrace();

        }

        fileWriterObject.writeFiles(null);
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("TaxiData is null!");

    }


    /**
     * Tests writing output files when taxiData is not null
     * @author Jules
     */
    @Test
    public void writeFilesTestNonNullTaxiData(){


        try{

            // FIRST : DELETE THE OUTPUT FILES
            File f = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME);

            if (f.isDirectory()) {
                File fileNamePlaces = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_PLACES);
                File fileNameTopNJourneys = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_TOP_5);
                File filePlacesPerDriver = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_PLACES_PER_DRIVER);
                Files.deleteIfExists(fileNamePlaces.toPath());
                Files.deleteIfExists(fileNameTopNJourneys.toPath());
                Files.deleteIfExists(filePlacesPerDriver.toPath());
            }



            // FILLING TAXI DATA
            TaxiData taxiData = new TaxiData();
            TreeMap<String,Taxi> taxis = new TreeMap<>();
            TaxiTreeMap taxiTreeMap = new TaxiTreeMap(taxis);
            taxiTreeMap.addTaxi(new Taxi("BR204SV","Eric Smith","Mercedes"));
            taxiData.setTaxis(taxiTreeMap);

            /* *** */
            TreeMap<Integer,Destination> destinations = new TreeMap<>();
            DestinationTreeMap currentYearDestinations = new DestinationTreeMap(destinations);
            try {
                currentYearDestinations.addDestination2017(new Destination(1, "Palace of Holyroodhouse", 2.9, false));
            } catch(DuplicateIDException e) {

            }
            taxiData.setCurrentYearDestinations(currentYearDestinations);

            /* *** */

            TreeSet<Destination> lastYearDestinations = new TreeSet<>();
            DestinationTreeSet previousYearDestinations = new DestinationTreeSet(lastYearDestinations);
            previousYearDestinations.add(new Destination("Main Station"));
            taxiData.setPreviousYearDestinations(previousYearDestinations);

            /* *** */
            TreeMap<String,ArrayList<Journey>> journeys = new TreeMap<>();
            JourneyTreeMap journeyTreeMap = new JourneyTreeMap(journeys);
            journeyTreeMap.addJourney(new Journey(1,"BR204SV",2,15,43.4));
            taxiData.setJourneys(journeyTreeMap);

            /* *** */
            fileWriterObject.writeFiles(taxiData);



            // CHECKING IF THE OUTPUT FILES ARE THERE
            boolean result = false;

            File f2 = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME);

            if (f2.isDirectory()) {
                File fileNamePlaces2 = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_PLACES);
                File fileNameTopNJourneys2 = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_TOP_5);
                File filePlacesPerDriver2 = new File(DataFileWriter.OUTPUT_DIRECTORY_NAME + "/"+ DataFileWriter.FILE_NAME_PLACES_PER_DRIVER);

                if(fileNamePlaces2.exists() && !fileNamePlaces2.isDirectory()
                        && fileNameTopNJourneys2.exists() && !fileNameTopNJourneys2.isDirectory()
                        && filePlacesPerDriver2.exists() && !filePlacesPerDriver2.isDirectory()) {
                    result = true;
                }
            }

            assertEquals(true,result);


        }catch(Exception e){

            e.printStackTrace();

        }

    }


    @Test
    public void ioSuccess() {

        assertTrue(fileWriterObject.writeFile1(taxiDataTest) );
        assertTrue(fileWriterObject.writeFile2(taxiDataTest) );
        assertTrue(fileWriterObject.writeFile3(taxiDataTest) );
    }


    @Test(expected = NullPointerException.class)
    public void ioFailure() throws NullPointerException, IOException {

        assertFalse(fileWriterObject.writeFile1(null));
        assertFalse(fileWriterObject.writeFile2(null));
        assertFalse(fileWriterObject.writeFile3(null));
    }


}