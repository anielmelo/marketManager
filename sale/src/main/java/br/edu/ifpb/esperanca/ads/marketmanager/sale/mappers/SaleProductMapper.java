package br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleProductRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleProductResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Sale;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.SaleProduct;
import org.springframework.stereotype.Component;

@Component
public class SaleProductMapper {

    public SaleProduct toEntity(SaleProductRequestDTO dto, Sale sale) {
        SaleProduct saleProduct = new SaleProduct();

        saleProduct.setProductId(dto.productId());
        saleProduct.setQuantity(dto.quantity());
        saleProduct.setSale(sale);

        return saleProduct;
    }

    public SaleProductResponseDTO toSaleProductResponseDTO(SaleProduct saleProduct) {
        return new SaleProductResponseDTO(
                saleProduct.getId(),
                saleProduct.getProductId(),
                saleProduct.getQuantity(),
                saleProduct.getTotal()
        );
    }
}
