package com.journalapp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponse {

    private Current current;

    public class Current{
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        @JsonProperty("feelslike")
        private int feelsLike;

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public List<String> getWeatherDescriptions() {
            return weatherDescriptions;
        }

        public void setWeatherDescriptions(List<String> weatherDescriptions) {
            this.weatherDescriptions = weatherDescriptions;
        }

        public int getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(int feelsLike) {
            this.feelsLike = feelsLike;
        }
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}

