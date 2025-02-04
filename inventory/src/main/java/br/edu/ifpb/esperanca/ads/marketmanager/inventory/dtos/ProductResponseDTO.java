package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Receiving;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;

public record ProductResponseDTO(
        String name,
        String brand,
        Double cost,
        int availableQuantity,
        int totalQuantity,
        Supplier supplier,
        Receiving receiving) { }
