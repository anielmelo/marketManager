package br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos;

public record SupplierResponseDTO(
        Long id,
        String name,
        String cnpj,
        String address,
        String contact
) {}
