package com.example.gestioneviaggi.controller;

import com.example.gestioneviaggi.dto.ViaggioDTO;
import com.example.gestioneviaggi.enumration.TipoStato;
import com.example.gestioneviaggi.service.ViaggioService;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {
  @Autowired private ViaggioService viaggioService;

  @GetMapping
  public ResponseEntity<Page<ViaggioDTO>> getAllViaggi(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(viaggioService.getAllViaggi(page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ViaggioDTO> getViaggioById(@PathVariable Long id) {
    ViaggioDTO viaggioDTO = viaggioService.getViaggioById(id);
    return ResponseEntity.ok(viaggioDTO);
  }

  @PostMapping
  public ResponseEntity<ViaggioDTO> createViaggio(@Valid @RequestBody ViaggioDTO dto) {
    ViaggioDTO nuvoViaggio = viaggioService.createViaggio(dto);
    URI location = URI.create("/api/viaggi/" + nuvoViaggio.getId());
    return ResponseEntity.created(location).body(nuvoViaggio);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ViaggioDTO> updateViaggio(
      @PathVariable Long id, @Valid @RequestBody ViaggioDTO dto) {
    ViaggioDTO updatedViaggio = viaggioService.updateViaggio(id, dto);
    return ResponseEntity.ok(updatedViaggio);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ViaggioDTO> deleteViaggio(@PathVariable Long id) {
    viaggioService.deleteViaggio(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<ViaggioDTO> updateViaggioStatus(@PathVariable Long id, @Valid @RequestParam TipoStato nuovoStato){
    ViaggioDTO updatedViaggio = viaggioService.updateViaggioStatoById(id, nuovoStato);
    return ResponseEntity.ok(updatedViaggio);
  }
}
