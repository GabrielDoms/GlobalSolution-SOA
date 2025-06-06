package com.energy.monitor.model;

import java.util.List;

public class EnergyStatusResponse {
    private WeatherData weather;
    private Alert alert;
    private List<String> advice;

    public EnergyStatusResponse(WeatherData weather, Alert alert, List<String> advice) {
        this.weather = weather;
        this.alert = alert;
        this.advice = advice;
    }

    public WeatherData getWeather() { return weather; }
    public Alert getAlert() { return alert; }
    public List<String> getAdvice() { return advice; }
}
