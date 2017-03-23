package test.model;

import main.model.Stats;
import main.model.Window;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * @author George Chiotis
 */
public class StatsTest {


    private Window [] windows;
    private Stats stats;



    @Before
    public void init() {


        windows = new Window[3];

        for(int i =0; i<3; i++)
            windows[i] = new Window( null, i );


        windows[0].setTotalNumberOfAllocatedTaxis(5);
        windows[0].setTotalNumberOfGroupsServed(8);
        windows[0].setTotalNumberOfPassengersServed(20);
        windows[0].setWorkingEndTime(7000);
        windows[0].setWorkingStartTime(0);

        windows[1].setTotalNumberOfAllocatedTaxis(5);
        windows[1].setTotalNumberOfGroupsServed(4);
        windows[1].setTotalNumberOfPassengersServed(6);
        windows[1].setWorkingEndTime(4000);
        windows[1].setWorkingStartTime(0);

        windows[2].setTotalNumberOfAllocatedTaxis(2);
        windows[2].setTotalNumberOfGroupsServed(3);
        windows[2].setTotalNumberOfPassengersServed(4);
        windows[2].setWorkingEndTime(4000);
        windows[2].setWorkingStartTime(0);


        stats = new Stats(null, windows);
    }





    @Test
    public void averageNumOfPassengerPerWindow() {

        assertEquals( stats.getAverageNumberOfPassengersPerWindow(), 10.0, 0.0 );
    }

    @Test
    public void averageNumOfTaxisPerWindow() {

        assertEquals( stats.getAverageTaxisPerWindow(), 4.0, 0.0 );
    }

    @Test
    public void averageGroupsPerWindow(){

        assertEquals( stats.getAverageGroupsPerWindow(), 5.0, 0.0 );
    }

    @Test
    public void averageWorkingTimePerWindow(){

        assertEquals( stats.getAverageWorkingTimePerWindow(), 5.0, 0.0);
    }



    @Test
    public void numberOfPassengersThisWindow(){

        assertEquals( stats.getNumberOfPassengerThisWindow(0), 20, 0.0);
        assertEquals( stats.getNumberOfPassengerThisWindow(1), 6, 0.0);
        assertEquals( stats.getNumberOfPassengerThisWindow(2), 4, 0.0);
    }

    @Test
    public void numberOfGroupsThisWindow(){

        assertEquals(stats.getNumberOfGroupsThisWindow(0), 8, 0.0);
        assertEquals(stats.getNumberOfGroupsThisWindow(1), 4, 0.0);
        assertEquals(stats.getNumberOfGroupsThisWindow(2), 3, 0.0);

    }

    @Test
    public void numberOfTaxisThisWindow(){

        assertEquals( stats.getNumberOfTaxisThisWindow(0), 5, 0.0);
        assertEquals( stats.getNumberOfTaxisThisWindow(1), 5, 0.0);
        assertEquals( stats.getNumberOfTaxisThisWindow(2), 2, 0.0);
    }

    @Test
    public void workingTimeThisWindow(){

        assertEquals( stats.getWorkingTimeThisWindow(0), 7, 0.0);
        assertEquals( stats.getWorkingTimeThisWindow(1), 4, 0.0);
        assertEquals( stats.getWorkingTimeThisWindow(2), 4, 0.0);

    }




}
