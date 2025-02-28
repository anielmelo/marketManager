package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.SaleProduct;

public record SaleProductRequestDTO (
        Long id,
        Long productId,
        int quantity,
        Double total,
        SaleProduct saleProduct
) {}
