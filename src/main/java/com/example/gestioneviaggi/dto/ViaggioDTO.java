package com.example.gestioneviaggi.dto;

import com.example.gestioneviaggi.enumration.TipoStato;
import com.example.gestioneviaggi.model.Prenotazione;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Data
public class ViaggioDTO {
    private Long id;
    @NotNull(message = "Destinazione can not be Null!")
    private String destinazione;
    @NotNull(message = "Data can not be Null!")
    private LocalDate data;
    @NotNull(message = "Stato can not be Null!")
    private TipoStato stato;
    private List<Long> PrenotazioniIds= new ArrayList<>();

    public ViaggioDTO() {
        this.PrenotazioniIds = new ArrayList<>();
    }
}
