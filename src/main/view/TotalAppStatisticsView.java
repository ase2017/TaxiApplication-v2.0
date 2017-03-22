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


public class TotalAppStatisticsView {

    private Stats stats;

    public void initAndShowGUI() {

        JFrame frame = new JFrame("Window Statistics Summary");
        final JFXPanel fxPanel = new JFXPanel();
        frame.add(fxPanel);
        frame.setSize(1600, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        Platform.runLater(() -> initFX(fxPanel));
    }

    private void initFX(JFXPanel fxPanel) {

        Scene scene = createScene();
        fxPanel.setScene(scene);

    }

    private Scene createScene() {

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);

        bc.setTitle("Window Statistics Summary");
        xAxis.setLabel("Windows");
        yAxis.setLabel("Values");


        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Number Of Passengers");

        for( int i=0; i<stats.getWindows().length; i++ ) {

            if (  stats.getNumberOfPassengerThisWindow(i) < 0 )
                series1.getData().add( new XYChart.Data("Window" + i, 0 ));
            else
                series1.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfPassengerThisWindow(i)) );

        }

        if (   stats.getAverageNumberOfPassengersPerWindow() < 0 )
            series1.getData().add( new XYChart.Data( "Avg Passengers", 0 ) );
        else
            series1.getData().add( new XYChart.Data( "Avg Passengers", stats.getAverageNumberOfPassengersPerWindow() ) );



        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Number Of Taxis");

        for( int i=0; i<stats.getWindows().length; i++ ) {

            if (  stats.getNumberOfTaxisThisWindow(i) < 0 )
                series2.getData().add( new XYChart.Data("Window" + i, 0 ));
            else
                series2.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfTaxisThisWindow(i)) );

        }

        if (  stats.getAverageTaxisPerWindow() < 0 )
            series2.getData().add( new XYChart.Data("Avg Taxis", 0 ));
        else
            series2.getData().add( new XYChart.Data( "Avg Taxis", stats.getAverageTaxisPerWindow() ) );



        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Number Of Groups");
        for( int i=0; i<stats.getWindows().length; i++ ) {

            if (  stats.getNumberOfGroupsThisWindow(i) < 0 )
                series3.getData().add( new XYChart.Data("Window" + i, 0 ));
            else
                series3.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfGroupsThisWindow(i) ));
        }

        if (  stats.getAverageGroupsPerWindow() < 0 )
            series3.getData().add( new XYChart.Data("Avg Groups", 0 ));
        else
            series3.getData().add( new XYChart.Data( "Avg Groups", stats.getAverageGroupsPerWindow() ) );


        Scene scene  = new Scene(bc,1600,900);

        bc.getData().addAll(series1, series2, series3);
        bc.setBarGap(40);
        bc.setCategoryGap(30);


        return (scene);
    }


    public void setStats(Stats stats){
        this.stats = stats;
    }

}