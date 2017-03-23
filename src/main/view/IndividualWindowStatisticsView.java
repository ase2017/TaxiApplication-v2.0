package main.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import main.model.Stats;
import javax.swing.*;


/**
 * Class that generates a Bar-Chart for a given Window
 * @author George C. and George G.
 */
public class IndividualWindowStatisticsView {

    private Stats stats;
    private int windowID;



    public IndividualWindowStatisticsView(int windowID){
        this.windowID = windowID;
    }


    /**
     * Create a Frame that contains the Bar-Chart.
     */
    public void initAndShowGUI() {

        JFrame frame = new JFrame("Window" + this.windowID + " Statistics");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(1600, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Platform.runLater(() -> initFX(fxPanel));
    }

    /**
     *
     * Add the scene (Bar Chart) onto the Panel.
     *
     * @param fxPanel, a new Panel.
     */
    private void initFX(JFXPanel fxPanel) {

        Scene scene = createScene();
        fxPanel.setScene(scene);

    }


    /**
     * Generate the Bar-Chart, that presents a given window's summary statistics.
     *
     * @return the Horizontal Bar-Chart
     */
    private Scene createScene() {


        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number,String> bc =
                new BarChart<Number,String>(xAxis,yAxis);


        // Set title and name the x- and y- axis.
        bc.setTitle("Window " + this.windowID + " Statistics Summary");
        xAxis.setLabel("Values");
        xAxis.setTickLabelRotation(90);     // Rotate 90 degrees, since this is a horizontal Bar-Chart.
        yAxis.setLabel("Window " + this.windowID);



        // Generate the bar that represents the number of Passengers served by this window.
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Number Of Passengers");
        if (stats.getNumberOfPassengerThisWindow(this.windowID) < 0 )
            series1.getData().add( new XYChart.Data( 0, "Num of Passengers") );
        else {

            series1.getData().add(new XYChart.Data(stats.getNumberOfPassengerThisWindow(this.windowID), "Num of Passengers"));

        }


        // Generate the bar that represents the number of Taxis served by this window.
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Number Of Taxis");
        if (stats.getNumberOfPassengerThisWindow(this.windowID) < 0 )
            series2.getData().add( new XYChart.Data( 0, "Num of Taxis" ) );
        else {

            series2.getData().add(new XYChart.Data( stats.getNumberOfTaxisThisWindow(this.windowID), "Num of Taxis" ));
        }


        // Generate the bar that represents the number of Groups served by this window.
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Number Of Groups");
        if (stats.getNumberOfPassengerThisWindow(this.windowID) < 0 )
            series3.getData().add( new XYChart.Data( 0, "Num of Groups" ) );
        else {

            series3.getData().add(new XYChart.Data( stats.getNumberOfGroupsThisWindow(this.windowID), "Num of Groups"));
        }


        // Generate the bar that represents the Working Time of this window in seconds.
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Working Time in secs");
        if (stats.getWorkingTimeThisWindow(this.windowID) < 0 )
            series4.getData().add( new XYChart.Data(0,"Working Time in secs") );
        else
            series4.getData().add(new XYChart.Data( stats.getWorkingTimeThisWindow(this.windowID), "Working Time in secs"));


        // Generate the bar that represents the Average Time per Window.
        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Average Working Time in secs");
        if (stats.getAverageWorkingTimePerWindow() < 0 )
            series5.getData().add( new XYChart.Data( 0, "Average Working Time in secs") );
        else
            series5.getData().add(new XYChart.Data( stats.getAverageWorkingTimePerWindow(), "Average Working Time in secs"));


        Scene scene  = new Scene(bc,1600,900);

        bc.getData().addAll( series1, series2, series3, series4, series5 );
        bc.setBarGap(0);
        bc.setCategoryGap(0);

        return (scene);
    }


    /*********************************************************
     *
     * Getters and Setters.
     *
     *********************************************************/

    public void setStats(Stats stats){
        this.stats = stats;
    }

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }


}
