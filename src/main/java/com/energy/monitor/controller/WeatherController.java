package com.energy.monitor.controller;

import com.energy.monitor.service.AlertService;
import com.energy.monitor.service.AdviceService;
import com.energy.monitor.service.WeatherService;
import com.energy.monitor.model.EnergyStatusResponse;
import com.energy.monitor.model.WeatherData;
import com.energy.monitor.model.WeatherRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@Tag(name = "Weather API", description = "API para monitoramento de condições climáticas e energia")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private AlertService alertService;

    @Autowired
    private AdviceService adviceService;

    @Operation(summary = "Obtém status de energia e alertas para uma localização")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Status obtido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Localização não encontrada")
    })
    @GetMapping("/status/{location}")
    public EnergyStatusResponse getEnergyStatus(@PathVariable String location) {
        String cidadeFormatada = formatarNomeCidade(location);
        WeatherRecord record = weatherService.getCurrentWeather(cidadeFormatada);
        var alert = alertService.generateAlert(record.getWeatherData());
        var advice = adviceService.getAdvice(alert);
        return new EnergyStatusResponse(record.getWeatherData(), alert, advice);
    }

    @Operation(summary = "Obtém dados meteorológicos atuais para uma localização")
    @GetMapping("/current/{location}")
    public ResponseEntity<WeatherRecord> getCurrentWeather(@PathVariable String location) {
        return ResponseEntity.ok(weatherService.getCurrentWeather(location));
    }

    @Operation(summary = "Lista todos os registros meteorológicos")
    @GetMapping
    public ResponseEntity<List<WeatherRecord>> getAllRecords() {
        return ResponseEntity.ok(weatherService.getAllRecords());
    }

    @Operation(summary = "Obtém um registro meteorológico específico por ID")
    @GetMapping("/{id}")
    public ResponseEntity<WeatherRecord> getRecordById(@PathVariable Long id) {
        WeatherRecord record = weatherService.getRecordById(id);
        return record != null ? ResponseEntity.ok(record) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Obtém registros meteorológicos por localização")
    @GetMapping("/location/{location}")
    public ResponseEntity<List<WeatherRecord>> getRecordsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(weatherService.getRecordsByLocation(location));
    }

    @Operation(summary = "Atualiza um registro meteorológico existente")
    @PutMapping("/{id}")
    public ResponseEntity<WeatherRecord> updateRecord(
            @PathVariable Long id,
            @RequestBody WeatherData newWeatherData) {
        WeatherRecord updated = weatherService.updateRecord(id, newWeatherData);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Remove um registro meteorológico específico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        weatherService.deleteRecord(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remove todos os registros meteorológicos")
    @DeleteMapping
    public ResponseEntity<Void> deleteAllRecords() {
        weatherService.deleteAllRecords();
        return ResponseEntity.ok().build();
    }

    private String formatarNomeCidade(String cidade) {
        return cidade.replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}
