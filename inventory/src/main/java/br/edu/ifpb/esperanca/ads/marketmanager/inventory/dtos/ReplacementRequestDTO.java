package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import jakarta.validation.constraints.NotNull;

public record ReplacementRequestDTO(
        @NotNull(message = "quantityReceived can not be null.") Integer quantityReceived,
        @NotNull(message = "totalPurchaseValue can not be null.") Double totalPurchaseValue,
        @NotNull(message = "productId can not be null.") Long productId
) { }
