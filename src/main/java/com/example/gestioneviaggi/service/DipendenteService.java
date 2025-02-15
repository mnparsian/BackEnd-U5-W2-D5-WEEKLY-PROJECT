package com.example.gestioneviaggi.service;

import com.example.gestioneviaggi.dto.DipendenteDTO;
import com.example.gestioneviaggi.mapper.DipendenteMapper;
import com.example.gestioneviaggi.model.Dipendente;
import com.example.gestioneviaggi.repository.DipendenteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class DipendenteService {

  @Autowired private DipendenteRepository dipendenteRepository;
  @Autowired private DipendenteMapper dipendenteMapper;

  // GET ALL DEPENDENTS
  public Page<DipendenteDTO> getAlldipendente(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Dipendente> dipendetePage = dipendenteRepository.findAll(pageable);
    return dipendetePage.map(dipendenteMapper::toResponseDto);
  }

  // GET DEPENDENT BY ID
  public DipendenteDTO getDipendenteById(Long id) {
    Dipendente dipendente =
        dipendenteRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Dependent with id(" + id + ") not found!"));
    return dipendenteMapper.toResponseDto(dipendente);
  }

  // POST NEW DEPENDENT
  public DipendenteDTO createNewDipendente(@Valid DipendenteDTO dto) {
    if (dipendenteRepository.existsByUsername(dto.getUsername())) {
      throw new IllegalArgumentException("The dependent with this username is already exist");
    }
    if (dipendenteRepository.existsByEmail(dto.getEmail())) {
      throw new IllegalArgumentException("This email is already exist");
    }
    Dipendente dipendente = dipendenteMapper.toEntity(dto);
    dipendente = dipendenteRepository.save(dipendente);
    return dipendenteMapper.toResponseDto(dipendente);
  }

  // UPDATE DEPENDENT
  public DipendenteDTO updateDipendenteById(Long id, @Valid DipendenteDTO dto) {
    Dipendente dipendente =
        dipendenteRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Dependent with id(" + id + ") not found!"));
    dipendente.setNome(dto.getNome());
    dipendente.setCognome(dto.getCognome());
    dipendente.setUsername(dto.getUsername());
    dipendente.setEmail(dto.getEmail());

    dipendente = dipendenteRepository.save(dipendente);
    return dipendenteMapper.toResponseDto(dipendente);
  }

  public void deleteDipendente(Long id) {
    if (!dipendenteRepository.existsById(id)) {
      throw new RuntimeException("Dependent with id(" + id + ") not found!");
    }
    dipendenteRepository.deleteById(id);
  }
}
