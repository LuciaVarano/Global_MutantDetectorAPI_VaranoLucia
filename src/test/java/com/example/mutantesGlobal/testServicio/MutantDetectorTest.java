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

    @Test
    public void testMutantWithDiagonalInverseSequence() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTATAT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testNonMutantWithOnlyThreeConsecutive() {
        String[] dna = {
                "AAATGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithExactlyTwoSequences() {
        String[] dna = {
                "AAAAGA",
                "CAGTGC",
                "TTTTGT",
                "AGAAGG",
                "CCTCTA",
                "TCACTG"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testNonMutantAlternatingPattern() {
        String[] dna = {
                "ATATATAT",
                "TATATATA",
                "ATATATAT",
                "TATATATA",
                "ATATATAT",
                "TATATATA",
                "ATATATAT",
                "TATATATA"
        };
        assertFalse(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithVerticalAndHorizontal() {
        String[] dna = {
                "AAAATG",
                "TGCAGT",
                "GCTTCC",
                "CCCCTC",
                "GTAGTC",
                "AGTCAC"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantMinimumSize4x4() {
        String[] dna = {
                "AAAA",
                "TGCA",
                "GCTA",
                "TTTT"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testNonMutantAllDifferent() {
        String[] dna = {
                "ATGC",
                "CGTA",
                "TACG",
                "GCAT"
        };
        assertFalse(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithDiagonalsOnly() {
        String[] dna = {
                "ATGCGA",
                "CAGTAC",
                "TTATGT",
                "AGAAGG",
                "CCTCTA",
                "TCACTG"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantLargeMatrix10x10() {
        String[] dna = {
                "ATGCGATTTT",
                "CAGTGCAAAT",
                "TTATGTAAAG",
                "AGAAGGCCCC",
                "CCCCTATGCA",
                "TCACTGAAAA",
                "ATGCGATCGA",
                "CAGTGCATGC",
                "TTATGTTACG",
                "AGAAGGTCGA"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testNonMutantNearMiss() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "CCGTCA",
                "TCACTG"
        };
        assertFalse(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithOverlappingSequences() {
        String[] dna = {
                "AAAAAA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCCC",
                "TCACTG"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }

    @Test
    public void testMutantWithAllSameBase() {
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertTrue(MutantDetector.isMutant(dna));
    }
}
