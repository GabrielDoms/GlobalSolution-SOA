package com.energy.monitor.repository;

import com.energy.monitor.model.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherRecord, Long> {
    List<WeatherRecord> findByLocationIgnoreCase(String location);
} 