package com.example.gestioneviaggi.dto;

import com.example.gestioneviaggi.enumration.TipoStato;
import com.example.gestioneviaggi.model.Prenotazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ViaggioDTO {
    private Long id;
    private String destinazione;
    private LocalDate data;
    private TipoStato stato;
    private List<Long> PrenotazioniIds;
}
