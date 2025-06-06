package com.energy.monitor.service;

import com.energy.monitor.model.WeatherData;
import com.energy.monitor.model.WeatherRecord;
import com.energy.monitor.model.WeatherDescription;
import com.energy.monitor.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Arrays;

@Service
public class WeatherService {
    private final String apiKey = "71977ab0faf88b8bb5768d73d6e92302"; // API Key da OpenWeatherMap
    private final String url = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric"; // URL da API OpenWeatherMap 
    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.restTemplate = new RestTemplate();
    }

    public WeatherRecord getCurrentWeather(String location) {
        String cidadeUrl = URLEncoder.encode(location, StandardCharsets.UTF_8);
        WeatherData weatherData = restTemplate.getForObject(
            String.format(url, cidadeUrl, apiKey), 
            WeatherData.class
        );
        
        // Converte o array de WeatherDescription em List
        if (weatherData != null && weatherData.getWeather() != null) {
            weatherData.setWeather(Arrays.asList(weatherData.getWeather().toArray(new WeatherDescription[0])));
        }
        
        WeatherRecord record = new WeatherRecord(location, weatherData);
        return weatherRepository.save(record);
    }

    public List<WeatherRecord> getAllRecords() {
        return weatherRepository.findAll();
    }

    public WeatherRecord getRecordById(Long id) {
        return weatherRepository.findById(id).orElse(null);
    }

    public List<WeatherRecord> getRecordsByLocation(String location) {
        return weatherRepository.findByLocationIgnoreCase(location);
    }

    public WeatherRecord updateRecord(Long id, WeatherData newWeatherData) {
        return weatherRepository.findById(id)
            .map(record -> {
                record.setWeatherData(newWeatherData);
                record.setTimestamp(java.time.LocalDateTime.now());
                return weatherRepository.save(record);
            })
            .orElse(null);
    }

    public void deleteRecord(Long id) {
        weatherRepository.deleteById(id);
    }

    public void deleteAllRecords() {
        weatherRepository.deleteAll();
    }
}
