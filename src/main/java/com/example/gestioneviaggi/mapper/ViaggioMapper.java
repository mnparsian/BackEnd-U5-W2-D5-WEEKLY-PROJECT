package com.example.gestioneviaggi.mapper;

import com.example.gestioneviaggi.dto.ViaggioDTO;
import com.example.gestioneviaggi.model.Viaggio;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ViaggioMapper {

  // FOR GET
  public ViaggioDTO toResponseDto(Viaggio v) {
    ViaggioDTO viaggioDTO = new ViaggioDTO();
    viaggioDTO.setId(v.getId());
    viaggioDTO.setDestinazione(v.getDestinazione());
    viaggioDTO.setData(v.getData());
    viaggioDTO.setStato(v.getStato());

    viaggioDTO.setPrenotazioniIds(
        v.getListaPrenotazioni().stream().map(p -> p.getId()).collect(Collectors.toList()));

    return viaggioDTO;
  }

  // FOR POST
  public Viaggio toEntity(ViaggioDTO dto) {
    Viaggio viaggio = new Viaggio();
    viaggio.setDestinazione(dto.getDestinazione());
    viaggio.setData(dto.getData());
    viaggio.setStato(dto.getStato());
    return viaggio;
  }
}
