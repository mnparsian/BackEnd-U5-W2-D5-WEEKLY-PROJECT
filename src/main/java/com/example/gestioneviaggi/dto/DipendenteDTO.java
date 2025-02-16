package com.example.gestioneviaggi.dto;

import com.example.gestioneviaggi.model.Prenotazione;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DipendenteDTO {
    public Long id;
    @NotNull(message = "username can not be Null!")
    private String username;
    private String nome;
    private String cognome;
    @NotNull(message = "email can not be Null!")
    private String email;
    private List<Long> PrenotazioniIds = new ArrayList<>();
    private String imageUrl;


}
