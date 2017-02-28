package test.io;

import main.io.DataFileReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class DataFileReaderTest {


    private String directoryNameLegal = "inputFiles/";
    private String testNameLegal = "testFiles/";
    private String filenameLegal = "taxis.txt";
    private String directoryNameIllegal = "NoDir/";
    private String filenameIllegal = "noFile.txt";
    private String filenameTaxiUnitTesting = "taxiTesting.txt";
    private String filenameLastYearDestTesting = "destin2016Testing.txt";
    private String filenameCurrentYearDestTesting = "destin2017Testing.txt";
    private String filenameJourneyTesting = "journeyTesting.txt";

    DataFileReader fileReaderObject = null;

    @Before
    public void loadFunc() {

        fileReaderObject = new DataFileReader();

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();




    /***********************************************************************************************************************
     *
     * DataFileReader: Open the default file under the default directory.
     *
     ***********************************************************************************************************************/

    @Test
    public void ioSuccess() {

        assertNotNull( "Error: Return null object!", fileReaderObject.taxiChecker(directoryNameLegal, filenameLegal) );
    }


    /***********************************************************************************************************************
     *
     * DataFileReader: Fail to open file or to find the directory.
     *
     ***********************************************************************************************************************/





    /***********************************************************************************************************************
     *
     * DataFileReader: T A X I Unit Testing
     *
     ***********************************************************************************************************************/

    @Test
    public void processTaxiLine() {

        // valid line
        String correctLine = "BR204SV,Eric Smith,Mercedes";
        assertTrue(fileReaderObject.processTaxiLine(correctLine));

        //          INVALID BRAND NAME
        // empty brand name
        String emptyBrandName = "XX743HE,Antonio Ivanov,";
        assertFalse(fileReaderObject.processTaxiLine(emptyBrandName));

        // space brand name
        String spaceBrandName = "XX743HE,Antonio Ivanov,  ";
        assertFalse(fileReaderObject.processTaxiLine(spaceBrandName));

        // brand not in enum, but accepted at this stage
        String wrongBrandName = "XX345JR,Walter Scott,AbrandNameNotinOurEnumList";
        assertTrue(fileReaderObject.processTaxiLine(wrongBrandName));

        //          INVALID DRIVER NAME
        // empty driver name
        String emptyDriverName = "XX123UV,,Nissan";
        assertFalse(fileReaderObject.processTaxiLine(emptyDriverName));

        // space driver name
        String spaceDriverName = "XX123UV,  ,Nissan";
        assertFalse(fileReaderObject.processTaxiLine(spaceDriverName));

        // only one word
        String tooShortDriverName = "XX478HS,Marco,Mercedes";
        assertFalse(fileReaderObject.processTaxiLine(tooShortDriverName));

        // only one character
        String tooShortDriverName2 = "XX842VV,D,Mercedes";
        assertFalse(fileReaderObject.processTaxiLine(tooShortDriverName2));

        // too long name, but accepted at this stage
        String tooLongDriverName = "XX247GE,AnameThatContains moreThan30Characters12323435,Toyota";
        assertTrue(fileReaderObject.processTaxiLine(tooLongDriverName));


        //          INVALID REGISTRATION NUMBER
        // empty reg
        String emptyRegistrationNumber = ",Nicholas Ivanov,Toyouta";
        assertFalse(fileReaderObject.processTaxiLine(emptyRegistrationNumber));

        // space reg
        String spaceRegistrationNumber = "  ,Nicholas Ivanov,Toyouta";
        assertFalse(fileReaderObject.processTaxiLine(spaceRegistrationNumber));

        // wrong pattern, accepted at this stage
        String wrongPatternRegistrationNumber = "XXXXXXX,Peter Parker,Bentley";
        assertTrue(fileReaderObject.processTaxiLine(wrongPatternRegistrationNumber));



        //          EXTREME CASE

        // empty string
        String nullString = null;
        assertFalse(fileReaderObject.processTaxiLine(nullString));

        // empty string
        String extremeCase0 = "";
        assertFalse(fileReaderObject.processTaxiLine(extremeCase0));

        // not enough commas
        String extremeCase00 = ",,";
        assertFalse(fileReaderObject.processTaxiLine(extremeCase00));

        // all 3 fields empty
        String extremeCase1 = ",,,";
        assertFalse(fileReaderObject.processTaxiLine(extremeCase1));

        // too many commas
        String extremeCase2 = ",,,,,,,,,,,,,,,,,,,,";
        assertFalse(fileReaderObject.processTaxiLine(extremeCase2));
    }


    /***********************************************************************************************************************
     *
     * DataFileReader: Last's Year DESTINATION Unit Testing.
     *
     ***********************************************************************************************************************/

    @Test
    public void processDestination2016Line() {

        // valid name
        String validDestination2016 = "Main Station";
        assertTrue(fileReaderObject.processDestination2016Line(validDestination2016));

        // valid name
        String invalidDestination2016 = "Napier University,Ocean Terminal";
        assertTrue(fileReaderObject.processDestination2016Line(invalidDestination2016));

        // valid name (more than 3 characters, less than 30)
        String validDestination20162 = ",,,,";
        assertTrue(fileReaderObject.processDestination2016Line(validDestination20162));

        // invalid name, but accepted at this stage (less than 3 characters)
        String invalidDestination20163 = ",";
        assertTrue(fileReaderObject.processDestination2016Line(invalidDestination20163));

        // invalid name, but accepted at this stage (more than 30 characters)
        String invalidDestination201634 = "........................................................................................................................";
        assertTrue(fileReaderObject.processDestination2016Line(invalidDestination201634));

        // empty name
        String invalidDestination20164 = "";
        assertFalse(fileReaderObject.processDestination2016Line(invalidDestination20164));

        // null name
        String invalidDestination20165 = null;
        assertFalse(fileReaderObject.processDestination2016Line(invalidDestination20165));
    }


    /***********************************************************************************************************************
     *
     * DataFileReader: Current's Year DESTINATION Unit Testing.
     *
     ***********************************************************************************************************************/
    @Test
    public void processDestination2017Line(){

        // valid line
        String validLine = "1,Palace of Holyroodhouse,2.9,N";
        assertTrue(fileReaderObject.processDestination2017Line(validLine));

        //          INVALID DESTINATION ID
        // id is 0, but accepted at this stage
        String invalidLine0 = "0,Palace of Holyroodhouse,2.9,N";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine0));

        // id is negative, but accepted at this stage
        String invalidLine1 = "-1,Palace of Holyroodhouse,2.9,N";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine1));

        // id is String
        String invalidLine2 = "A,Palace of Holyroodhouse,2.9,N";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine2));

        // id is empty
        String invalidLine3 = "";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine3));


        //          INVALID DESTINATION NAME

        // empty
        String invalidLine4 = "50,,6.6,Y";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine4));

        // invalid destination name but accepted at this stage (too short)
        String invalidLine5 = "51,XX,0.4,N";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine5));

        // invalid destination name but accepted at this stage (too long)
        String invalidLine6 = "52,ThisISAdestinationNameWithMoreThan30Characters,0.4,N";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine6));


        //          INVALID DISTANCE
        // invalid but accepted at this stage (negative)
        String invalidLine7 = "61,Heriot Watt University,-1,Y";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine7));

        // invalid but accepted at this stage (0)
        String invalidLine8 = "62,Napier University,0.0,N";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine8));

        // invalid but accepted at this stage (too fast)
        String invalidLine9 = "63,George Street,1000000003,N";
        assertTrue(fileReaderObject.processDestination2017Line(invalidLine9));

        // invalid (empty)
        String invalidLine10 = "63,George Street,,N";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine10));

        // invalid (space)
        String invalidLine102 = "63,George Street,  ,N";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine102));

        // invalid (String)
        String invalidLine1023 = "63,George Street,AA,N";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine1023));

        //          INVALID URBAN

        // invalid (empty)
        String invalidLine11 = "71,Heriot Watt University,7.2,";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine11));

        // invalid (space)
        String invalidLine112 = "72,Heriot Watt University,7.2,  ";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine112));

        // invalid but (not Y or N)
        String invalidLine12 = "73,Napier University,5.0,X";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine12));

        // invalid (integer)
        String invalidLine13 = "74,George Street,10,23";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine13));



        //          EXTREME CASE
        // invalid
        String invalidLine14 = ",,,\t";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine14));

        // invalid
        String invalidLine15 = ",,,,,,,,,,,,,,,,,";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine15));

        // invalid (empty)
        String invalidLine16 = "";
        assertFalse(fileReaderObject.processDestination2017Line(invalidLine16));
    }

    /***********************************************************************************************************************
     *
     * DataFileReader: J O U R N E Y Unit Testing.
     *
     ***********************************************************************************************************************/

    @Test
    public void processJourneyLine(){

        //          Invalid ID
        // invalid but accepted at this stage (0)
        String invalidLine1 = "0,PP204SV,2,15,43.4";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine1));

        // invalid but accepted at this stage (negative)
        String invalidLine2 = "-10,XX204SV,3,33,52.3";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine2));

        // invalid (string)
        String invalidLine3 = "ABC,XX367DG,2,21,39.1";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine3));

        // invalid (missing)
        String invalidLine4 = ",PP204SV,2,15,43.4";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine4));

        // invalid (spaces)
        String invalidLine5 = "  ,PP204SV,2,15,43.4";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine5));


        //          Invalid Reg number
        // invalid but accepted at this stage (0)
        String invalidLine6 = "51,,2,15,43.4";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine6));

        // invalid but accepted at this stage
        String invalidLine7 = "52,QQQQQQQ,3,33,52.3";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine7));

        // invalid (spaces)
        String invalidLine8 = "0,  ,2,15,43.4";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine8));


        //          Invalid number of passengers
        // invalid but accepted at this stage (0)
        String invalidLine9 = "61,XX204SV,0,33,52.3";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine9));

        // invalid but accepted at this stage (-1)
        String invalidLine10 = "62,XX345JR,-1,10,73.5";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine10));

        // invalid but accepted at this stage (2000)
        String invalidLine11 = "63,TT345JR,2000,33,52.7";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine11));

        // invalid (string)
        String invalidLine12 = "64,BB345JR,AVG,33,52.7";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine12));

        // invalid (empty)
        String invalidLine13 = "65,LX120RG,,31,62.4";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine13));

        // invalid (spaces)
        String invalidLine14 = "66,LX120RG,  ,31,62.4";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine14));


        //          Invalid time
        // invalid but accepted at this stage (0)
        String invalidLine15 = "80,KU247GE,2,0,43.8";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine15));

        // invalid but accepted at this stage (-1)
        String invalidLine16 = "81,CU247GE,2,-1,43.8";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine16));

        // invalid (empty)
        String invalidLine17 = "82,LL247GE,2,,43.8";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine17));

        // invalid (spaces)
        String invalidLine18 = "83,LL247GE,2,  ,43.8";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine18));

        // invalid (string)
        String invalidLine19 = "83,LL247GE,2,ABC,43.8";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine19));




        //          Invalid maximum velocity
        // invalid but accepted at this stage (-1)
        String invalidLine20 = "91,YR204YR,3,33,-1";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine20));

        // invalid but accepted at this stage (too big)
        String invalidLine21 = "92,GR883GR,3,8,1000000034";
        assertTrue(fileReaderObject.processJourneyLine(invalidLine21));

        // invalid (empty)
        String invalidLine22 = "93,UU345UU,3,8,";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine22));

        // invalid (spaces)
        String invalidLine23 = "94,UU345UU,3,8,  ";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine23));

        // invalid (spaces)
        String invalidLine24 = "95,UU345UU,3,8,ABC";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine24));

        //          Extreme case

        // invalid (spaces)
        String invalidLine25 = "";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine25));

        // invalid (spaces)
        String invalidLine26 = ",,,,";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine26));

        // invalid (spaces)
        String invalidLine27 = ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,";
        assertFalse(fileReaderObject.processJourneyLine(invalidLine27));
    }

}