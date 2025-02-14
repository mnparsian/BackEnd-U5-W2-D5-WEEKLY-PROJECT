package com.example.gestioneviaggi.dto;

import com.example.gestioneviaggi.model.Prenotazione;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DipendenteDTO {
    public Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private List<Long> PrenotazioniIds;
}
