package com.example.gestioneviaggi.controller;

import com.example.gestioneviaggi.dto.DipendenteDTO;
import com.example.gestioneviaggi.model.Dipendente;
import com.example.gestioneviaggi.service.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

  @Autowired private DipendenteService dipendenteService;

  @GetMapping
  public ResponseEntity<Page<DipendenteDTO>> getallDipendente(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(dipendenteService.getAlldipendente(page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DipendenteDTO> getDipendenteById(@PathVariable Long id){
   return ResponseEntity.ok(dipendenteService.getDipendenteById(id));
  }

  @PostMapping
  public ResponseEntity<DipendenteDTO> createDipendente(@Valid @RequestBody DipendenteDTO dto){
    DipendenteDTO nuovoDipendente = dipendenteService.createNewDipendente(dto);
    URI location = URI.create("/api/dipendenti/" + nuovoDipendente.getId());
    return ResponseEntity.created(location).body(nuovoDipendente);
  }
  @PostMapping("/upload-photo/{id}")
  public ResponseEntity<String> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
    try {
      String imageUrl = dipendenteService.uploadImage(id, file);
      return ResponseEntity.ok(imageUrl);
    } catch (IOException e) {
      return ResponseEntity.status(500).body("Error uploading image: " + e.getMessage());
    }
  }

 // @PostMapping("/upload-photo")
  public ResponseEntity<DipendenteDTO> createDipendenteWithPhoto(@RequestParam("file") MultipartFile file,@Valid @RequestBody DipendenteDTO dto) throws IOException {
    DipendenteDTO nuovoDipendente = dipendenteService.createNewDipendente(dto);
    dipendenteService.uploadImage(nuovoDipendente.getId(),file);
    URI location = URI.create("/api/dipendenti/"+nuovoDipendente.getId());
    return ResponseEntity.created(location).body(nuovoDipendente);
  }//

  @PostMapping(value = "/create-with-photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DipendenteDTO> createDipendenteWithPhoto(
          @RequestPart("dipendente") @Valid DipendenteDTO dipendenteDTO,
          @RequestPart("file") MultipartFile file) {
    try {

      DipendenteDTO savedDipendente = dipendenteService.createNewDipendente(dipendenteDTO);

      String imageUrl = dipendenteService.uploadImage(savedDipendente.getId(), file);
      savedDipendente.setImageUrl(imageUrl);
      return ResponseEntity.status(HttpStatus.CREATED).body(savedDipendente);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
  }



  @PutMapping("/{id}")
  public ResponseEntity<DipendenteDTO> updateDipendente(@PathVariable Long id,@Valid @RequestBody DipendenteDTO dto){
    return ResponseEntity.ok(dipendenteService.updateDipendenteById(id,dto));
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDipendente(@PathVariable Long id){
    dipendenteService.deleteDipendente(id);
    return ResponseEntity.noContent().build();
  }
}
