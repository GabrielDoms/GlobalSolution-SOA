package com.energy.monitor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.ElementCollection;
import java.util.List;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @Embedded
    private WeatherMain main;

    @Embedded
    private WeatherWind wind;

    @ElementCollection
    private List<WeatherDescription> weather;

    public WeatherMain getMain() { return main; }
    public void setMain(WeatherMain main) { this.main = main; }

    public WeatherWind getWind() { return wind; }
    public void setWind(WeatherWind wind) { this.wind = wind; }

    public List<WeatherDescription> getWeather() { return weather; }
    public void setWeather(List<WeatherDescription> weather) { this.weather = weather; }

    public String getWeatherDescription() {
        if (weather != null && !weather.isEmpty()) {
            return weather.get(0).getDescription();
        } else {
            return "Sem descrição do clima";
        }
    }
}

