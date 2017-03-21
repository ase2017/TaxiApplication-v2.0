package main.model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



/**
 * Created by Jules and George on 3/21/2017.
 */
public class ViewTotalWindowsChart extends Application {



        static private Stats stats;


        @Override public void start(Stage stage) {


            stage.setTitle("Window Total Statistics in Chart.");

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            final BarChart<String,Number> bc =
                    new BarChart<String,Number>(xAxis,yAxis);

            bc.setTitle("Window Statistics Summary");
            xAxis.setLabel("Windows");
            yAxis.setLabel("Values");


            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Number Of Passengers");

            for( int i=0; i<stats.windows.length; i++ ) {

                series1.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfPassengerThisWindow(i)) );

            }

            series1.getData().add( new XYChart.Data( "Avg Num Of Passengers", stats.getAverageNumberOfPassengersPerWindow() ) );



            XYChart.Series series2 = new XYChart.Series();
            series2.setName("Number Of Taxis");

            for( int i=0; i<stats.windows.length; i++ ) {

                series2.getData().add( new XYChart.Data("Window" + i, stats.getNumberOfTaxisThisWindow(i)) );

            }

            series2.getData().add( new XYChart.Data( "Avg Num Of Taxis", stats.getAverageTaxisPerWindow() ) );



            XYChart.Series series3 = new XYChart.Series();
            series3.setName("Number Of Groups");
            for( int i=0; i<stats.windows.length; i++ ) {

                series3.getData().add( new XYChart.Data("Window" + i, 30 ));
            }

            series3.getData().add( new XYChart.Data( "Avg Num Of Groups", stats.getAverageGroupsPerWindow() ) );




            Scene scene  = new Scene(bc,800,600);

            bc.getData().addAll(series1, series2, series3);
            bc.setBarGap(3);
            bc.setCategoryGap(20);

            stage.setScene(scene);
            stage.show();
        }


    public void exportSummaryStatisticsChart(){

        launch();
    }

    public void setStats(Stats stats){
        this.stats = stats;
    }



}



