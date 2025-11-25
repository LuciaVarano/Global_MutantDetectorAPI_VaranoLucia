package com.example.mutantesGlobal.controllers;

import com.example.mutantesGlobal.Servicios.MutantDetector;
import com.example.mutantesGlobal.dto.DnaRequest;
import com.example.mutantesGlobal.dto.DnaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mutant")
public class MutantController {
    private final MutantDetector mutantDetector;

    public MutantController(MutantDetector mutantDetector) {
        this.mutantDetector = mutantDetector;
    }


    @PostMapping
    @Operation(
            summary = "Detectar si un ADN corresponde a un mutante",
            description = "Analiza una secuencia de ADN y determina si pertenece a un mutante. Un individuo es considerado mutante cuando se encuentran más de una secuencia de cuatro letras idénticas de forma consecutiva en sentido horizontal, vertical u oblicuo (diagonal)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Es mutante",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DnaResponse.class),
                            examples = @ExampleObject(value = "{\"mutant\": true}")
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Es humano",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DnaResponse.class),
                            examples = @ExampleObject(value = "{\"mutant\": false}")
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos - ADN nulo, vacío, matriz no cuadrada o caracteres inválidos",
                    content = @Content
            )
    })



    public ResponseEntity<DnaResponse> checkMutant(@Valid @RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantDetector.analyzeDna(dnaRequest.getDna());
        DnaResponse dnaResponse = new DnaResponse(isMutant);
        if (isMutant) {
            return ResponseEntity.ok(dnaResponse);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(dnaResponse);
        }
    }

}
