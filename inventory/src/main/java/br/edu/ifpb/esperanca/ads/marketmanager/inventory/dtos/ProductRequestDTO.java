package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Receiving;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;
import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(
        @NotBlank(message = "Name can not be blank.") String name,
        @NotBlank(message = "Brand can not be blank.") String brand,
        Double cost,
        Integer availableQuantity,
        Integer totalQuantity,
        Supplier supplierId,
        Receiving receivingId) { }
