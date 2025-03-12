package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Discount;

import java.util.List;

public record SaleRequestDTO(
        Discount discount,
        List<SaleProductRequestDTO> saleProductRequestDTOList
) {}
