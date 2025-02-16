package com.example.gestioneviaggi.controller;

import com.example.gestioneviaggi.dto.PrenotazioneDTO;
import com.example.gestioneviaggi.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<Page<PrenotazioneDTO>> getAllPrenotazione(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(prenotazioneService.getAllPrenotazioni(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> getPrenotaioneById(@PathVariable Long id){
        return ResponseEntity.ok(prenotazioneService.getPrenotazioneById(id));
    }

    @PostMapping
    public ResponseEntity<PrenotazioneDTO> createPrenotaione(@Valid @RequestBody PrenotazioneDTO dto){
        PrenotazioneDTO prenotaione = prenotazioneService.createPrenotazione(dto);
        URI location = URI.create("/api/prenotazioni/"+prenotaione.getId());
        return ResponseEntity.created(location).body(prenotaione);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PrenotazioneDTO> updatePrenotazione(@PathVariable Long id,@Valid @RequestBody PrenotazioneDTO dto){
        return ResponseEntity.ok(prenotazioneService.updatePrenotazioneById(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione (@PathVariable Long id){
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }
}
