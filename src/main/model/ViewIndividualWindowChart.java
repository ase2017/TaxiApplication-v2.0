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
public class ViewIndividualWindowChart extends  Application {


    static Stats stats;
    static int windowIndex;


    @Override
    public void start(Stage stage) {


        stage.setTitle("Window " + windowIndex + "Stats.");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();

        final BarChart<String, Number> bc =
                new BarChart<String, Number>(xAxis, yAxis);

        bc.setTitle("Window" + windowIndex + " Stats.");
        xAxis.setLabel("Window " + windowIndex );
        yAxis.setLabel("Values");


        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Number Of Passengers");
        series1.getData().add(new XYChart.Data("Num of Passengers", stats.getNumberOfPassengerThisWindow(windowIndex)));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Number Of Taxis");
        series2.getData().add(new XYChart.Data("Num of Taxis", stats.getNumberOfTaxisThisWindow(windowIndex)));


        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Number Of Groups");
        series3.getData().add(new XYChart.Data("Num of Groups", stats.getNumberOfGroupsThisWindow(windowIndex)));


        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Working Time");
        series4.getData().add(new XYChart.Data("Working Time", stats.getWorkingTimeThisWindow(windowIndex)));


        XYChart.Series series5 = new XYChart.Series();
        series5.setName("Average Working Time");
        series5.getData().add(new XYChart.Data("Average Working Time", stats.getAverageWorkingTimePerWindow()));


        Scene scene  = new Scene(bc,800,600);

        bc.getData().addAll(series1, series2, series3);
        bc.setBarGap(3);
        bc.setCategoryGap(20);

        stage.setScene(scene);
        stage.show();


    }



    public void popWindowChart(){

        launch();
    }

    public void setWindowData(Stats stats, int windowIndex){

        this.stats = stats;
        this.windowIndex = windowIndex;

        System.out.println("\t Window " + windowIndex );
    }


}
