package app.controllers;

import java.time.LocalDate;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {
    int week = 0;
    LocalDate today = LocalDate.now();
    LocalDate monday = today.with(previousOrSame(MONDAY));
    LocalDate sunday = today.with(nextOrSame(SUNDAY));

    final static String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    final static double[] hrs1 = {4,5,3,6,8,2,7,3};
    final static double[] hrs2 = {3,2,5,8,3,5,6,7};
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
    XYChart.Series series = new XYChart.Series();
    XYChart.Data<String,Number>[] data = new XYChart.Data[days.length];

    @FXML
    private Label weekText;
    @FXML
    private AnchorPane reportsChartContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeChart();
    weekText.setText(this.monday+" - "+this.sunday);
    setData(hrs1);
    }

    private void setDate(String type){
        switch (type){
            case "prev":
                this.monday = this.monday.minusDays(7);
                this.sunday = this.sunday.minusDays(7);
                break;
            case "next":
                this.monday = this.monday.plusDays(7);
                this.sunday = this.sunday.plusDays(7);
                break;
        }
        weekText.setText(this.monday+" - "+this.sunday);
    }

    @FXML
    private void prev(){
        week--;
        setDate("prev");
        setData(hrs2);
    }

    @FXML
    private void next(){
    if (week<0){
        week++;
        setDate("next");
        setData(hrs2);
    }
    }

    private void setData(double[] hrs){
        for (int i = 0; i < days.length; i++) {
            data[i] = new XYChart.Data<>(days[i], hrs[i]);
        }
        series.getData().clear();
        bc.getData().clear();
        series.getData().addAll(data);
        bc.getData().addAll(series);
        reportsChartContainer.getChildren().clear();
        reportsChartContainer.getChildren().add(bc);
    }

    private void initializeChart(){
        bc.setPrefWidth(700);
        bc.setPrefHeight(600);
        bc.setId("reportsChart");
        bc.setTitle("Tracking Summary");
        xAxis.setLabel("Week Days");
        yAxis.setLabel("Hours");
        bc.setLegendVisible(false);
    }
}
