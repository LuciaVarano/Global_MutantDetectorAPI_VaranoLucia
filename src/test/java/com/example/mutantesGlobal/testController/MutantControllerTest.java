package com.example.mutantesGlobal.testController;

import com.example.mutantesGlobal.Servicios.MutantDetector;
import com.example.mutantesGlobal.controllers.MutantController;
import com.example.mutantesGlobal.dto.DnaRequest;
import com.example.mutantesGlobal.dto.DnaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class MutantControllerTest {

    @Mock
    private MutantDetector mutantDetector;

    @InjectMocks
    private MutantController mutantController;

    @Test
    void whenDnaIsMutant_returnsOkStatus() {
        // Arrange
        String[] mutantDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        DnaRequest dnaRequest = new DnaRequest();
        dnaRequest.setDna(mutantDna);
        when(mutantDetector.analyzeDna(any(String[].class))).thenReturn(true);

        // Act
        ResponseEntity<DnaResponse> response = mutantController.checkMutant(dnaRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isMutant());
        verify(mutantDetector, times(1)).analyzeDna(mutantDna);
    }

    @Test
    void whenDnaIsHuman_returnsForbiddenStatus() {
        // Arrange
        String[] humanDna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        DnaRequest dnaRequest = new DnaRequest();
        dnaRequest.setDna(humanDna);
        when(mutantDetector.analyzeDna(any(String[].class))).thenReturn(false);

        // Act
        ResponseEntity<DnaResponse> response = mutantController.checkMutant(dnaRequest);

        // Assert
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertFalse(response.getBody().isMutant());
        verify(mutantDetector, times(1)).analyzeDna(humanDna);
    }

    @Test
    void whenMultipleMutantChecks_callsServiceMultipleTimes() {
        // Arrange
        String[] dna1 = {"AAAA", "CCCC", "TCAG", "GGTC"};
        String[] dna2 = {"ATGC", "ATGC", "ATGC", "ATGC"};
        DnaRequest request1 = new DnaRequest();
        request1.setDna(dna1);
        DnaRequest request2 = new DnaRequest();
        request2.setDna(dna2);

        when(mutantDetector.analyzeDna(dna1)).thenReturn(true);
        when(mutantDetector.analyzeDna(dna2)).thenReturn(true);

        // Act
        mutantController.checkMutant(request1);
        mutantController.checkMutant(request2);

        // Assert
        verify(mutantDetector, times(1)).analyzeDna(dna1);
        verify(mutantDetector, times(1)).analyzeDna(dna2);
    }
}