package com.energy.monitor.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_records")
public class WeatherRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String location;

    @Embedded
    private WeatherData weatherData;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public WeatherRecord() {}

    public WeatherRecord(String location, WeatherData weatherData) {
        this.location = location;
        this.weatherData = weatherData;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public WeatherData getWeatherData() { return weatherData; }
    public void setWeatherData(WeatherData weatherData) { this.weatherData = weatherData; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
} 