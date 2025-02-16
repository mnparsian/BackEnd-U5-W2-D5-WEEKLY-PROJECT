package com.example.gestioneviaggi.service;

import com.cloudinary.utils.ObjectUtils;
import com.example.gestioneviaggi.config.CloudinaryConfig;
import com.example.gestioneviaggi.dto.DipendenteDTO;
import com.example.gestioneviaggi.exception.DuplicatePrenotazioneException;
import com.example.gestioneviaggi.mapper.DipendenteMapper;
import com.example.gestioneviaggi.model.Dipendente;
import com.example.gestioneviaggi.repository.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

import java.io.IOException;

@Service
@Validated
public class DipendenteService {

  @Autowired private DipendenteRepository dipendenteRepository;
  @Autowired private DipendenteMapper dipendenteMapper;
  @Autowired private CloudinaryConfig cloudinary;

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
            .orElseThrow(() -> new EntityNotFoundException("Dependent with id(" + id + ") not found!"));
    return dipendenteMapper.toResponseDto(dipendente);
  }

  // POST NEW DEPENDENT
  public DipendenteDTO createNewDipendente(@Valid DipendenteDTO dto) {
    if (dipendenteRepository.existsByUsername(dto.getUsername())) {
      throw new DuplicatePrenotazioneException("The dependent with this username is already exist");
    }
    if (dipendenteRepository.existsByEmail(dto.getEmail())) {
      throw new DuplicatePrenotazioneException("This email is already exist");
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
            .orElseThrow(() -> new EntityNotFoundException("Dependent with id(" + id + ") not found!"));
    dipendente.setNome(dto.getNome());
    dipendente.setCognome(dto.getCognome());
    dipendente.setUsername(dto.getUsername());
    dipendente.setEmail(dto.getEmail());

    dipendente = dipendenteRepository.save(dipendente);
    return dipendenteMapper.toResponseDto(dipendente);
  }

  public void deleteDipendente(Long id) {
    if (!dipendenteRepository.existsById(id)) {
      throw new EntityNotFoundException("Dependent with id(" + id + ") not found!");
    }
    dipendenteRepository.deleteById(id);
  }

  //UPLOAD IMAGE
  public String uploadImage(Long id, MultipartFile file) throws IOException {
    Dipendente dipendente = dipendenteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Dipendente not found"));

    Map uploadResult =
        cloudinary.uploader().uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    String imageUrl = (String) uploadResult.get("url");

    dipendente.setImageUrl(imageUrl);
    dipendenteRepository.save(dipendente);

    return imageUrl;
  }

}
