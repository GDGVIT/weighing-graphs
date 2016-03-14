package com.gdgvitvellore.abhinav.clientside_weighinggraphs;


public class ChartElements {
    private String temperature ;
    private String airQuality ;
    private String weight ;
    private String date ;
    private String time ;

    public ChartElements() {
    }

    public ChartElements(String temperature, String airQuality, String weight, String date, String time) {
        this.temperature = temperature;
        this.airQuality = airQuality;
        this.weight = weight;
        this.date = date;
        this.time = time;
    }

    public ChartElements(String weight, String date, String time) {
        this.weight = weight;
        this.date = date;
        this.time = time;
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
