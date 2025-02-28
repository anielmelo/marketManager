package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import java.time.LocalDateTime;

public record ReplacementResponseDTO(
        Long id,
        String idStockKeeper,
        LocalDateTime date,
        Double totalPurchaseValue,
        Integer quantityReceived) { }
