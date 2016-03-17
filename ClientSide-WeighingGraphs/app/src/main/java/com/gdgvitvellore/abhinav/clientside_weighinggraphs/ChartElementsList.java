package com.gdgvitvellore.abhinav.clientside_weighinggraphs;


import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ChartElementsList {


    @SerializedName("data")
    private List<ChartElement> chartElements;

    public List<ChartElement> getChartElements() {
        return chartElements;
    }

    public void setChartElements(List<ChartElement> chartElements) {
        this.chartElements = chartElements;
    }


    public class ChartElement {


        public String temperature;
        public String airQuality;
        public String weight;
        public String date;
        public String time;

        public ChartElement() {
        }

        public ChartElement(String temperature, String airQuality, String weight, String date) {
            this.temperature = temperature;
            this.airQuality = airQuality;
            this.weight = weight;
            ExtractDateTime(time);
        }

        public ChartElement(String airQuality, String time) {
            this.airQuality = airQuality;
            ExtractDateTime(time);
        }

        private void ExtractDateTime(String time) {
            setDate(time.substring(0, 10));
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
}