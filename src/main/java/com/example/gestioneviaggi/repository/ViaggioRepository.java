package com.example.gestioneviaggi.repository;

import com.example.gestioneviaggi.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ViaggioRepository extends JpaRepository<Viaggio,Long> {
    public boolean existsByDestinazioneAndData(String destinazione , LocalDate data);
}
