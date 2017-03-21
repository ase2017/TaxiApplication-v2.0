package test.model;

import main.model.*;
import org.junit.Before;
import org.junit.Test;


public class StatsTest {


    private Window [] windows;
    private Stats stats;



    @Before
    public void init() {


        windows = new Window[3];

        for(int i =0; i<3; i++)
            windows[i] = new Window( null, i );


        windows[0].setTotalNumberOfAllocatedTaxis(5);
        windows[0].setTotalNumberOfGroupsServed(3);
        windows[0].setTotalNumberOfPassengersServed(8);
        windows[0].setWorkingEndTime(2000);
        windows[0].setWorkingStartTime(0);

        windows[1].setTotalNumberOfAllocatedTaxis(4);
        windows[1].setTotalNumberOfGroupsServed(2);
        windows[1].setTotalNumberOfPassengersServed(14);
        windows[1].setWorkingEndTime(5000);
        windows[1].setWorkingStartTime(0);

        windows[2].setTotalNumberOfAllocatedTaxis(2);
        windows[2].setTotalNumberOfGroupsServed(2);
        windows[2].setTotalNumberOfPassengersServed(4);
        windows[2].setWorkingEndTime(6000);
        windows[2].setWorkingStartTime(0);


        stats = new Stats(null, windows);
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
