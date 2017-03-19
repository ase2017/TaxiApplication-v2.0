package main.model;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;



public class ViewCharts  extends Application {


    private Stats statistics;
    private int windowIndex;
    private long totalTime;

    public ViewCharts( Stats statistics, int windowIndex, long totalTime ) {

        this.statistics = statistics;
        this.windowIndex = windowIndex;
        this.totalTime = totalTime;

    }



    @Override public void start( Stage stage ) throws Exception {


        Scene scene = new Scene(new Group());
        stage.setTitle("Window " + windowIndex + " Pie Chart");
        stage.setWidth(500);
        stage.setHeight(500);

        ObservableList<PieChart.Data> pieChartData;
        pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Working Time", statistics.getWorkingTimeThisWindow(windowIndex)),
                new PieChart.Data("Idle Time", totalTime - statistics.getWorkingTimeThisWindow(windowIndex))
        );

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle( "Working time for Window " + windowIndex );

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }



}
