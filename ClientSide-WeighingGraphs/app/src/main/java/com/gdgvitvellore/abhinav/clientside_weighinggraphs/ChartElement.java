package com.gdgvitvellore.abhinav.clientside_weighinggraphs;


import java.util.List;

public class ChartElement {

    private List<ChartElement> chartElements ;

    public List<ChartElement> getChartElements() {
        return chartElements;
    }

    public void setChartElements(List<ChartElement> chartElements) {
        this.chartElements = chartElements;
    }

    private String temperature ;
    private String airQuality ;
    private String weight ;
    private String date ;
    private String time ;

    public ChartElement() {
    }

    public ChartElement(List<ChartElement> chartElements, String airQuality, String weight, String time) {
        this.chartElements = chartElements;
        this.airQuality = airQuality;
        this.weight = weight;
        ExtractDateTime(time);
    }

    public ChartElement(String temperature, String airQuality, String weight, String date) {
        this.temperature = temperature;
        this.airQuality = airQuality;
        this.weight = weight;
        ExtractDateTime(time);
    }

    public ChartElement(String weight, String time) {
        this.weight = weight;
        ExtractDateTime(time);
    }

    private void ExtractDateTime(String time){
        setDate(time.substring(0,10));
        setTime(time.substring(12));
    }


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getAirQuality() {
        return airQuality;
    }

    public void setAirQuality(String airQuality) {
        this.airQuality = airQuality;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
