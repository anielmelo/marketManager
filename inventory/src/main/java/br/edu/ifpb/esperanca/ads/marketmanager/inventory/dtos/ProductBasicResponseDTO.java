package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

public record ProductBasicResponseDTO(
        Long id,
        String name,
        String brand,
        Double cost,
        int availableQuantity,
        SupplierResponseDTO supplier
) { }
