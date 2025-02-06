package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReceivingResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Receiving;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories.ReceivingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceivingService {
    private static final Logger log = LoggerFactory.getLogger(ReceivingService.class);

    private final ReceivingRepository receivingRepository;

    @Autowired
    public ReceivingService(ReceivingRepository receivingRepository) {
        this.receivingRepository = receivingRepository;
    }

    public ReceivingResponseDTO createReceiving(LocalDateTime date) {
        log.info("Creating a new receiving record...");

        if (date.isAfter(LocalDateTime.now())) {throw new RuntimeException("Error");}

        Receiving receiving = new Receiving();
        receiving.setDate(date);
        receivingRepository.save(receiving);
        log.info("Receiving record created successfully.");
        return new ReceivingResponseDTO(receiving.getId(),receiving.getDate());
    }

    public ReceivingResponseDTO getReceivingDtoById(Long id) {
        log.info("Fetching receiving with id: {}", id);
        Receiving receiving = findReceivingEntity(id);
        log.info("Receiving record found.");
        return new ReceivingResponseDTO(receiving.getId(), receiving.getDate());
    }

    public List<ReceivingResponseDTO> getAllReceivings() {
        log.info("Fetching all receiving records...");
        return receivingRepository.findAll()
                .stream()
                .map(r -> new ReceivingResponseDTO(r.getId(), r.getDate()))
                .collect(Collectors.toList());
    }

    protected Receiving findReceivingEntity(Long id) {
        return receivingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receiving record not found"));
    }
}