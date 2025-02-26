package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

public record SaleProductResponseDTO (
        Long id,
        Long productId,
        int quantity,
        Double total
) {}
