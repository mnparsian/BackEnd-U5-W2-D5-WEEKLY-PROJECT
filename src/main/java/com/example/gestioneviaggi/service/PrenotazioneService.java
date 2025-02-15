package com.example.gestioneviaggi.service;

import com.example.gestioneviaggi.dto.PrenotazioneDTO;
import com.example.gestioneviaggi.mapper.PrenotazioneMapper;
import com.example.gestioneviaggi.model.Dipendente;
import com.example.gestioneviaggi.model.Prenotazione;
import com.example.gestioneviaggi.model.Viaggio;
import com.example.gestioneviaggi.repository.DipendenteRepository;
import com.example.gestioneviaggi.repository.PrenotazioneRepository;
import com.example.gestioneviaggi.repository.ViaggioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private PrenotazioneMapper prenotazioneMapper;
    @Autowired
    private ViaggioRepository viaggioRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;

    //GET ALL RESERVATIONS
    public Page<PrenotazioneDTO> getAllPrenotazioni(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Prenotazione> prenotazionePage = prenotazioneRepository.findAll(pageable);
        return prenotazionePage.map(prenotazioneMapper::toResponseDto);
    }
    //GET RESERVATION BY ID
    public PrenotazioneDTO getPrenotazioneById(Long id){
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(()->new RuntimeException("Reservation with id(" + id + ") not found!"));
        return prenotazioneMapper.toResponseDto(prenotazione);
    }
    //POST NEW RESERVATION
    public PrenotazioneDTO createPrenotazione(@Valid PrenotazioneDTO dto){
        if(prenotazioneRepository.existsByDipendenteIdAndDataPrenotazione(dto.getDipendenteId(),dto.getDataPrenotazione())){
            throw new IllegalArgumentException("The employee already has a reservation on this date.");
        }
        Viaggio viaggio = viaggioRepository.findById(dto.getViaggioId())
                .orElseThrow(() -> new RuntimeException("Trip with id( " + dto.getViaggioId() + ") not founded!"));

        Dipendente dipendente = dipendenteRepository.findById(dto.getDipendenteId())
                .orElseThrow(() -> new RuntimeException("Dependent with id( " + dto.getDipendenteId() + ") not founded!"));
        Prenotazione prenotazione = prenotazioneMapper.toEntity(dto);
        prenotazione.setViaggioId(viaggio.getId());
        prenotazione.setDipendenteId(dipendente.getId());
        prenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toResponseDto(prenotazione);
    }

    //UPDATE RESERVATION BY ID
    public PrenotazioneDTO updatePrenotazioneById(Long id, @Valid PrenotazioneDTO dto){
        Prenotazione prenotazione = prenotazioneRepository.findById(id).orElseThrow(()-> new RuntimeException("Reservation with id(" + id + ") not found!"));
        prenotazione = prenotazioneMapper.toEntity(dto);
        prenotazione = prenotazioneRepository.save(prenotazione);
        return prenotazioneMapper.toResponseDto(prenotazione);
    }

    //DELETE RESERVATION
    public void deletePrenoyazione(Long id){
        if(!prenotazioneRepository.existsById(id)){
            throw new RuntimeException("Reservation with id(" + id + ") not found!");
        }
        prenotazioneRepository.deleteById(id);
    }
}
