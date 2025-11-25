package com.example.mutantesGlobal.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    @Hidden
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "online");
        response.put("message", "Servicio de análisis genético de mutantes");
        response.put("endpoints", Map.of(
                "verificar_mutante", "POST /mutant — Verifica si el ADN corresponde a un mutante",
                "estadisticas", "GET /stats — Muestra estadísticas de validaciones",
                "documentacion", "GET /swagger-ui/index.html — Documentación interactiva"
        ));
        return response;
    }
}