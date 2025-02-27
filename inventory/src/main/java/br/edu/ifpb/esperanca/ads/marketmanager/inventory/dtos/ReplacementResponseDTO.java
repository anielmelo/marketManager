package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import java.time.LocalDateTime;

public record ReplacementResponseDTO(
        Long id,
        LocalDateTime date,
        Double totalPurchaseValue,
        Integer quantityReceived) { }
