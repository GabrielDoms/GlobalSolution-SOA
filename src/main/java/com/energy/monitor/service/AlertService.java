package com.energy.monitor.service;

import com.energy.monitor.model.Alert;
import com.energy.monitor.model.WeatherData;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    public Alert generateAlert(WeatherData data) {
        Alert alert = new Alert();
        if (data.getWind().getSpeed() > 50 || data.getWeatherDescription().contains("storm")) {
            alert.setLevel("High");
            alert.setMessage("⚠️ Risco de queda de energia. Prepare-se!");
        } else if (data.getMain().getTemp() > 35) {
            alert.setLevel("Moderate");
            alert.setMessage("⚠️ Calor intenso pode causar sobrecarga.");
        } else {
            alert.setLevel("Low");
            alert.setMessage("Sem riscos significativos de queda de energia.");
        }
        return alert;
    }
}
