package com.energy.monitor.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class WeatherDescription {
    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
