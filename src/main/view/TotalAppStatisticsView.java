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
 * Class that generates a Bar-Chart for Summary Statistics for the whole process.
 * @author George C. and George G.
 */
public class TotalAppStatisticsView {

    private Stats stats;

    /**
     * Create a Frame that contains the Bar-Chart.
     */
    public void initAndShowGUI() {

        JFrame frame = new JFrame("Window Statistics Summary");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(1600, 900 );
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
     * @return the Bar-Chart
     */
    private Scene createScene() {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);


        // Set tile and name x- and y- axis.
        bc.setTitle("Window Statistics Summary");
        xAxis.setLabel("Windows");
        yAxis.setLabel("Values");



        // Generate the bar that represents the number of Passengers served by each window
        // and place it in the corresponding position in the Bar-Chart.
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Number Of Passengers");

        for( int i=0; i<stats.getWindows().length; i++ ) {  // For each window

            if (  stats.getNumberOfPassengerThisWindow(i) < 0 ) {   // If there is no such window, a -1 will be returned.

                // Then place a 0 value to the corresponding position of the Bar-Chart.
                series1.getData().add( new XYChart.Data("Window" + i, 0 ));
            }
            else {
                // Place the number of Passengers served by i-th Window.
                series1.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfPassengerThisWindow(i)) );
            }


        }

        if (   stats.getAverageNumberOfPassengersPerWindow() < 0 ) {    // If the average Number of Passengers is negative

            // Place a 0 value to the corresponding position of the Bar-Chart.
            series1.getData().add( new XYChart.Data( "Respective Average", 0 ) );
        }
        else {

            // Place the Average number of Passengers to the corresponding position and use the same color as Number Of Passengers per Window.
            series1.getData().add(new XYChart.Data("Respective Average", stats.getAverageNumberOfPassengersPerWindow()));
        }



        // Generate the bar that represents the number of Taxis served by each window
        // and place it in the corresponding position in the Bar-Chart.
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Number Of Taxis");

        for( int i=0; i<stats.getWindows().length; i++ ) {  // For each window.

            if (  stats.getNumberOfTaxisThisWindow(i) < 0 ) {   // If there is no such window, a -1 will be returned.

                // Place a 0 value to the corresponding position of the Bar-Chart.
                series2.getData().add( new XYChart.Data("Window" + i, 0 ));
            }
            else {
                //  Place the number of Taxis served by i-th Window.
                series2.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfTaxisThisWindow(i)) );
            }

        }



        if (  stats.getAverageTaxisPerWindow() < 0 ) {  // If the average Number of Taxis is negative

            // Place a 0 value to the corresponding position of the Bar-Chart.
            series2.getData().add( new XYChart.Data("Respective Average", 0 ));
        }
        else {
            // Place the Average number of Taxis to the corresponding position and use the same color as Number Of Taxis per Window.
            series2.getData().add( new XYChart.Data( "Respective Average", stats.getAverageTaxisPerWindow() ) );
        }




        // Generate the bar that represents the number of Groups served by each window
        // and place it in the corresponding position in the Bar-Chart.
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Number Of Groups");
        for( int i=0; i<stats.getWindows().length; i++ ) {  // For each window

            if (  stats.getNumberOfGroupsThisWindow(i) < 0 ) {  // If there is no such window, a -1 will be returned.

                // Place a 0 value to the corresponding position of the Bar-Chart.
                series3.getData().add(new XYChart.Data("Window" + i, 0));
            }
            else {
                //  Place the number of Groups served by i-th Window.
                series3.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfGroupsThisWindow(i) ));
            }

        }



        if (  stats.getAverageGroupsPerWindow() < 0 ) {  // If the average Number of Groups is negative

            // Place a 0 value to the corresponding position of the Bar-Chart.
            series3.getData().add( new XYChart.Data("Respective Average", 0 ));
        }
        else {

            // Place the Average number of Groups to the corresponding position and use the same color as Number Of Groups per Window.
            series3.getData().add( new XYChart.Data( "Respective Average", stats.getAverageGroupsPerWindow() ) );
        }



        Scene scene  = new Scene(bc,1600,900);
        bc.getData().addAll(series1, series2, series3);

        // Modify the spacing between the Bars.
        bc.setBarGap(30);
        bc.setCategoryGap(10);


        return (scene);
    }


    /*************************************
     *
     * Setter Method.
     **************************************/
    public void setStats(Stats stats){
        this.stats = stats;
    }

}