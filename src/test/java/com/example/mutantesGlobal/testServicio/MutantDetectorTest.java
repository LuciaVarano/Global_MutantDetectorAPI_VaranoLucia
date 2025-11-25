package com.example.mutantesGlobal.testServicio;

import com.example.mutantesGlobal.Servicios.MutantDetector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MutantDetectorTest {

    @Test
    public void testMutantWithHorizontalSequence() {
        String[] dna = {
                "AAAAGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithVerticalSequence() {
        String[] dna = {
                "AGAATG",
                "AGCAGT",
                "AGTTCC",
                "AGTCTC",
                "GTAGTC",
                "GGTCAC"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithDiagonalSequence() {
        String[] dna = {
                "AGAATG",
                "TACAGT",
                "GCAGCC",
                "TTGATG",
                "GTAGTC",
                "AGTCAA"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testNonMutantDna() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithMultipleSequences() {
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testNonMutantSmallMatrix() {
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertFalse(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithComplexPattern() {
        String[] dna = {
                "TCGGGTGAT",
                "TGATCCTTT",
                "TACGAGTGA",
                "AAATGTACG",
                "ACGAGTGCT",
                "AGACACATG",
                "GAATTCCAA",
                "ACTACGACC",
                "TGAGTATCC"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }
}
