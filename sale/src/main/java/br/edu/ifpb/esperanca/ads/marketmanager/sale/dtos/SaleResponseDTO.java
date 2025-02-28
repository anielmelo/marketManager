package br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Discount;

import java.time.LocalDateTime;
import java.util.List;

public record SaleResponseDTO(
        Long id,
        LocalDateTime date,
        Double total,
        List<SaleProductResponseDTO> products,
        Discount discount
) {}
