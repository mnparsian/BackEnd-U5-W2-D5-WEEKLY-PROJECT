package com.example.gestioneviaggi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataPrenotazione;
    @Column(name = "viaggio_id", nullable = false)
    private Long viaggioId;

    @Column(name = "dipendente_id", nullable = false)
    private Long dipendenteId;
}
