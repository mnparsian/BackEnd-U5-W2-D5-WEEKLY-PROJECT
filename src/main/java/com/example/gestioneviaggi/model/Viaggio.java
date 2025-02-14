package com.example.gestioneviaggi.model;

import com.example.gestioneviaggi.enumration.TipoStato;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinazione;
    private LocalDate data;
    @Enumerated(value = EnumType.STRING)
    private TipoStato stato;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "viaggio_id")
    private List<Prenotazione> listaPrenotazioni;

    public Viaggio(String destinazione, LocalDate data, TipoStato stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
        this.listaPrenotazioni = new ArrayList<>();
    }
}
