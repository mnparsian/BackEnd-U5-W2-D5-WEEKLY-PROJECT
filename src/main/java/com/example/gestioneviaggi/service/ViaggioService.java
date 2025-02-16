package com.example.gestioneviaggi.service;

import com.example.gestioneviaggi.dto.ViaggioDTO;
import com.example.gestioneviaggi.enumration.TipoStato;
import com.example.gestioneviaggi.exception.DuplicatePrenotazioneException;
import com.example.gestioneviaggi.mapper.ViaggioMapper;
import com.example.gestioneviaggi.model.Viaggio;
import com.example.gestioneviaggi.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ViaggioService {

  @Autowired private ViaggioRepository viaggioRepository;
  @Autowired private ViaggioMapper viaggioMapper;

  // GET ALL TRIPS
  public Page<ViaggioDTO> getAllViaggi(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Viaggio> viaggoPage = viaggioRepository.findAll(pageable);
    return viaggoPage.map(viaggioMapper::toResponseDto);
  }

  // GET TRIP BY ID
  public ViaggioDTO getViaggioById(Long id) {
    Viaggio viaggio =
        viaggioRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Trip with id(" + id + ") not found!"));
    return viaggioMapper.toResponseDto(viaggio);
  }

  // POST NEW TRIP
  public ViaggioDTO createViaggio(@Valid ViaggioDTO dto) {
    if (viaggioRepository.existsByDestinazioneAndData(dto.getDestinazione(), dto.getData())) {
      throw new DuplicatePrenotazioneException(
          "The trip with this destination in this date is already exist");
    }
    Viaggio viaggio = viaggioMapper.toEntity(dto);
    viaggio = viaggioRepository.save(viaggio);
    return viaggioMapper.toResponseDto(viaggio);
  }

  // UPDATE TRIP
  public ViaggioDTO updateViaggio(Long id, @Valid ViaggioDTO dto) {
    Viaggio viaggio =
        viaggioRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Trip with id(" + id + ") not found!"));
    viaggio.setDestinazione(dto.getDestinazione());
    viaggio.setData(dto.getData());
    viaggio.setStato(dto.getStato());
    viaggio = viaggioRepository.save(viaggio);
    return viaggioMapper.toResponseDto(viaggio);
  }

  // DELETE TRIP BY ID
  public void deleteViaggio(Long id) {
    if (!viaggioRepository.existsById(id)) {
      throw new EntityNotFoundException("Trip with id(" + id + ") not found!");
    }
    viaggioRepository.deleteById(id);
  }

  // UPDATE TRIP STATUS
  public ViaggioDTO updateViaggioStatoById(Long id, TipoStato nuovoStato) {
    Viaggio viaggio =
        viaggioRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Trip with id(" + id + ") not found!"));
    viaggio.setStato(nuovoStato);
    viaggio = viaggioRepository.save(viaggio);
    return viaggioMapper.toResponseDto(viaggio);
  }
}
