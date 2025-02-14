package com.example.gestioneviaggi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrenotazioneDTO {
    private Long id;
    private LocalDate dataPrenotazione;
    private Long viaggioId;
    private Long dipendenteId;
}
