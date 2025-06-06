package com.energy.monitor.service;

import com.energy.monitor.model.Alert;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdviceService {
    public List<String> getAdvice(Alert alert) {
        return switch (alert.getLevel()) {
            case "High" -> List.of("Carregue seus dispositivos.", "Tenha lanternas à mão.", "Evite usar aparelhos de alto consumo.");
            case "Moderate" -> List.of("Reduza o uso de ar-condicionado.", "Mantenha-se hidratado.");
            default -> List.of("Fique atento a mudanças no clima.");
        };
    }
}
