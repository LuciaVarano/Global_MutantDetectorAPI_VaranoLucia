package com.example.mutantesGlobal.Servicios;

import com.example.mutantesGlobal.Repositorio.DnaRecordRepository;
import com.example.mutantesGlobal.dto.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private final DnaRecordRepository dnaRecordRepository;

    @Autowired
    public StatsService(DnaRecordRepository dnaRecordRepository) {
        this.dnaRecordRepository = dnaRecordRepository;
    }

    public StatsResponse getStats() {
        long countMutantDna = dnaRecordRepository.countByIsMutant(true);
        long countHumanDna = dnaRecordRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
        return new StatsResponse(countMutantDna, countHumanDna, ratio);
    }
}

