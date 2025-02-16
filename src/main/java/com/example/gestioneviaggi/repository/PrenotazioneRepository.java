package com.example.gestioneviaggi.repository;

import com.example.gestioneviaggi.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {

    @Query(value = "SELECT EXISTS (SELECT 1 FROM prenotazioni p JOIN viaggi v ON p.viaggio_id = v.id WHERE p.dipendente_id = :dipendenteId AND v.data = :data)", nativeQuery = true)
    boolean existsByDipendenteIdAndViaggioData(@Param("dipendenteId") Long dipendenteId, @Param("data") LocalDate data);





}
