package com.example.gestioneviaggi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;
    private String nome;
    private String cognome;
    @Column(nullable = false,unique = true)
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dipendente_id")
    private List<Prenotazione> listaPrenotazioni = new ArrayList<>();
    @Column(name = "image_url")
    private String imageUrl;

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.listaPrenotazioni = new ArrayList<>();
    }

}
