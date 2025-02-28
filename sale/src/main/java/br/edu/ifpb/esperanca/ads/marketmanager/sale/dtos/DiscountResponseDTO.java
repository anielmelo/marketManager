package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

public record DiscountResponseDTO (
        Long id,
        String idSaleKeeper,
        String code,
        String description,
        Double value,
        Double minimumValue,
        Boolean active
) {}
