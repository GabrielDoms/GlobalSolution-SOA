package com.energy.monitor.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class WeatherWind {
    private double speed;
    public double getSpeed() { return speed; }
    public void setSpeed(double speed) { this.speed = speed; }
}
