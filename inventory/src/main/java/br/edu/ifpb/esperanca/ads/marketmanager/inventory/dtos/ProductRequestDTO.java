package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.model.Receiving;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.model.Supplier;
import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(
        @NotBlank(message = "") String name,
        @NotBlank(message = "") String brand,
        Double cost,
        Integer availableQuantity,
        Integer totalQuantity,
        Supplier supplierId,
        Receiving receivingId) { }
