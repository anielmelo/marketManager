package br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleProductResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Sale;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.SaleProduct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    public Sale toEntity(SaleRequestDTO dto, List<SaleProduct> products) {
        Sale sale = new Sale();
        sale.setDate(dto.date());
        sale.setProducts(products);
        return sale;
    }

    public SaleResponseDTO toSaleResponseDTO(Sale sale) {
        List<SaleProductResponseDTO> products = sale.getProducts()
                .stream()
                .map(sp -> new SaleProductResponseDTO(
                        sp.getId(),
                        sp.getProductId(),
                        sp.getQuantity(),
                        sp.getTotal()
                ))
                .collect(Collectors.toList());

        return new SaleResponseDTO(
                sale.getId(),
                sale.getDate(),
                sale.getTotal(),
                products,
                sale.getDiscount()
        );
    }
}

