package br.edu.ifpb.esperanca.ads.marketmanager.inventory.controllers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReceivingRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReceivingResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.ReceivingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/receivings")
public class ReceivingController {
    private final static Logger log = LoggerFactory.getLogger(ReceivingController.class);
    private final ReceivingService receivingService;

    @Autowired
    public ReceivingController(ReceivingService receivingService) {
        this.receivingService = receivingService;
    }

    @PostMapping
    public ResponseEntity<ReceivingResponseDTO> registerNewReceiving(@RequestBody(required = false) ReceivingRequestDTO date) {
        log.info("Entering registerNewReceiving method. Date provided: {}", date);

        var obj = (date == null) ? receivingService.createReceiving(LocalDateTime.now()) : receivingService.createReceiving(date.date());

        log.info("New receiving registered. ID: {}", obj.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceivingResponseDTO> getReceiving(@PathVariable Long id) {
        log.info("Entering getReceiving method. Receiving ID: {}", id);

        var obj = receivingService.getReceivingDtoById(id);

        log.info("Receiving found. ID: {}", obj.id());
        return ResponseEntity.ok(obj);
    }

    @GetMapping
    public ResponseEntity<List<ReceivingResponseDTO>> getAll() {
        log.info("Entering getAll method. Retrieving all receivings.");

        List<ReceivingResponseDTO> receivings = receivingService.getAllReceivings();

        log.info("Total receivings retrieved: {}", receivings.size());
        return ResponseEntity.ok(receivings);
    }
}