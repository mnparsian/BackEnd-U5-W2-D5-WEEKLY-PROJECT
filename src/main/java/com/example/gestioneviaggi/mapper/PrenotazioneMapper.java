package com.example.gestioneviaggi.mapper;

import com.example.gestioneviaggi.dto.PrenotazioneDTO;
import com.example.gestioneviaggi.model.Prenotazione;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

  // FOR GET
  public PrenotazioneDTO toResponseDto(Prenotazione p) {
    PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO();
    prenotazioneDTO.setId(p.getId());
    prenotazioneDTO.setDataPrenotazione(p.getDataPrenotazione());
    prenotazioneDTO.setDipendenteId(p.getDipendenteId());
    prenotazioneDTO.setViaggioId(p.getViaggioId());
    prenotazioneDTO.setNote(p.getNote());
    return prenotazioneDTO;
  }

  // FOR POST
  public Prenotazione toEntity(PrenotazioneDTO dto) {
    Prenotazione prenotazione = new Prenotazione();
    prenotazione.setDataPrenotazione(dto.getDataPrenotazione());
    prenotazione.setDipendenteId(dto.getDipendenteId());
    prenotazione.setViaggioId(dto.getViaggioId());
    prenotazione.setNote(dto.getNote());

    return prenotazione;
  }
}
