package com.example.mutantesGlobal.Repositorio;

import com.example.mutantesGlobal.Entidades.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long>{
    Optional<DnaRecord> findByDna(String dnaSequence);

    long countByIsMutant(boolean isMutant);
}
