package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(
        @NotBlank(message = "Name can not be blank.") String name,
        @NotBlank(message = "Brand can not be blank.") String brand,
        Double cost,
        Long supplierId
) { }
