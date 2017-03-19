package test.model;

import main.model.Stats;
import main.model.TaxiData;
import main.model.Window;
import org.junit.Before;
import org.junit.Test;



public class StatsTest {


    private int numOfWindows = 3;
    private int numOfTaxis = 3;
    private int numOfGroups = 3;

    private TaxiData taxiData;
    private Window [] windows;
    private Thread[] windowsThreads;
    private Stats stats;




    @Before
    public void init() {


    }





    @Test
    public void averageNumOfPassengerPerWindow() {


    }

    @Test
    public void averageNumOfTaxisPerWindow() {

    }

    @Test
    public void averageGroupsPerWindow(){

    }

    @Test
    public void averageWorkingTimePerWindow(){

    }









    @Test
    public void numberOfPassengersThisWindow(){

    }

    @Test
    public void numberOfGroupsThisWindow(){

    }

    @Test
    public void numberOfTaxisThisWindow(){

    }

    @Test
    public void workingTimeThisWindow(){


    }







    @Test
    public void print() {

        stats.printIndividualWindowStats();
    }





}
