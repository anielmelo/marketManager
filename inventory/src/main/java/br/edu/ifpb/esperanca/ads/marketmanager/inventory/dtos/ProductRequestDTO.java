package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotBlank(message = "Name can not be blank.") String name,
        @NotBlank(message = "Brand can not be blank.") String brand,
        Double cost,
        @NotNull(message = "Available quantity can not be null.") Integer availableQuantity,
        Long supplierId,
        Long receivingId) { }
