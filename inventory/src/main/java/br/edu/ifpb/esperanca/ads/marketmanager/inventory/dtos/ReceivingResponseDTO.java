package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import java.time.LocalDateTime;

public record ReceivingResponseDTO(
        Long id,
        LocalDateTime date
) { }
