package com.example.mutantesGlobal.Entidades;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class DnaRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dna;

    private boolean isMutant;

    public DnaRecord(String dnaHash, boolean isMutant) {
        this.dna = dnaHash;
        this.isMutant = isMutant;
    }
}

