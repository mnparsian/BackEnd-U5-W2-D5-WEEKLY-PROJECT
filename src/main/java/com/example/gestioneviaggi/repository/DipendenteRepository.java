package com.example.gestioneviaggi.repository;

import com.example.gestioneviaggi.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.DataInput;

public interface DipendenteRepository extends JpaRepository<Dipendente,Long>
{}
