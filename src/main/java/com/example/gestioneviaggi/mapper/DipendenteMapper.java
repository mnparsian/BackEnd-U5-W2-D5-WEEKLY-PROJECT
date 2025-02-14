package com.example.gestioneviaggi.mapper;

import com.example.gestioneviaggi.dto.DipendenteDTO;
import com.example.gestioneviaggi.model.Dipendente;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DipendenteMapper {
  // FOR GET
  public DipendenteDTO toResponseDto(Dipendente d) {
    DipendenteDTO dipendenteDTO = new DipendenteDTO();
    dipendenteDTO.setId(d.getId());
    dipendenteDTO.setUsername(d.getUsername());
    dipendenteDTO.setNome(d.getNome());
    dipendenteDTO.setCognome(d.getCognome());
    dipendenteDTO.setEmail(d.getEmail());

    dipendenteDTO.setListaPrenotazioni(d.getListaPrenotazioni());

    return dipendenteDTO;
  }

  // FOR POST
  public Dipendente toEntity(DipendenteDTO dto) {
    Dipendente dipendente = new Dipendente();
    dipendente.setUsername(dto.getUsername());
    dipendente.setNome(dto.getNome());
    dipendente.setCognome(dto.getCognome());
    dipendente.setEmail(dto.getEmail());
    return dipendente;
  }
}
