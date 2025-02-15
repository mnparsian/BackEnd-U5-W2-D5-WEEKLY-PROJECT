package com.example.gestioneviaggi.repository;

import com.example.gestioneviaggi.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {
    public boolean existsByDipendenteIdAndDataPrenotazione(Long id, LocalDate data);
}
