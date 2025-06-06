package com.energy.monitor.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class WeatherMain {
    private double temp;
    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }
}
