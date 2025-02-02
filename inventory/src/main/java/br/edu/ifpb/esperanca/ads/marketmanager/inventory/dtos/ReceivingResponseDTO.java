package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import java.time.LocalDate;

public record ReceivingResponseDTO(
        Long id,
        LocalDate date
) { }
