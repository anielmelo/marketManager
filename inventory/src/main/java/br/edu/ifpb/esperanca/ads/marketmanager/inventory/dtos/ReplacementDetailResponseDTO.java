package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import java.time.LocalDateTime;

public record ReplacementDetailResponseDTO (
        Long id,
        LocalDateTime date,
        Double totalPurchaseValue,
        Integer quantityReceived,
        ProductBasicResponseDTO product
) {
}
