package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;


import java.util.List;

public record ProductResponseDTO(
        Long id,
        String name,
        String brand,
        Double cost,
        int availableQuantity,
        SupplierResponseDTO supplier,
        List<ReplacementResponseDTO> replacements) { }