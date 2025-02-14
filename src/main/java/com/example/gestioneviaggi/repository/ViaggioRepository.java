package com.example.gestioneviaggi.repository;

import com.example.gestioneviaggi.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio,Long> {}
