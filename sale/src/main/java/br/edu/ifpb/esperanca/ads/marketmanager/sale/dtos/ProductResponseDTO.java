package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

public record ProductResponseDTO (
        Long id,
        String name,
        String brand,
        Double cost,
        int availableQuantity
) {}
