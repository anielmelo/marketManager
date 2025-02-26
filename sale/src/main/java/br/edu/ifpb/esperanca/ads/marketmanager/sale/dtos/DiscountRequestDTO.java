package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

public record DiscountRequestDTO (
        String code,
        String description,
        Double value,
        Double minimumValue
) {}
