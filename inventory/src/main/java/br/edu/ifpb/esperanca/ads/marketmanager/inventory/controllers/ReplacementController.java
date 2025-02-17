package br.edu.ifpb.esperanca.ads.marketmanager.inventory.controllers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReplacementDetailResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReplacementRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.ReplacementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/replacements")
public class ReplacementController {
    private static final Logger log = LoggerFactory.getLogger(ReplacementController.class);
    private final ReplacementService replacementService;

    @Autowired
    public ReplacementController(ReplacementService replacementService) {
        this.replacementService = replacementService;
    }

    @PostMapping
    public ResponseEntity<ReplacementDetailResponseDTO> registerNewReplacement(@RequestBody ReplacementRequestDTO dto) {
        log.info("Received request to register new replacement: {}", dto);
        var response = replacementService.registerNewReplacement(dto);
        log.info("Replacement registered successfully with ID: {}", response.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplacementDetailResponseDTO> getReplacementById(@PathVariable Long id) {
        log.info("Received request to get replacement by ID: {}", id);
        return ResponseEntity.ok(replacementService.getReplacementById(id));
    }

    @GetMapping
    public ResponseEntity<List<ReplacementDetailResponseDTO>> getAllReplacements() {
        log.info("Received request to get all replacements");
        return ResponseEntity.ok(replacementService.getAllReplacements());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReplacementDetailResponseDTO> updateReplacement(@PathVariable Long id, @RequestBody ReplacementRequestDTO dto) {
        log.info("Received request to update replacement with ID: {}", id);
        return ResponseEntity.ok(replacementService.updateReplacement(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReplacement(@PathVariable Long id) {
        log.info("Received request to delete replacement with ID: {}", id);
        replacementService.deleteReplacement(id);
        log.info("Replacement with ID {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

}
