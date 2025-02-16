package com.example.gestioneviaggi.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "ViaggioId can not be Null!")
    private Long viaggioId;
    @NotNull(message = "DipendenteId can not be Null!")
    private Long dipendenteId;
    @Size(max = 500,message = "The note cannot be longer than 500 characters.")
    private String note;
}
