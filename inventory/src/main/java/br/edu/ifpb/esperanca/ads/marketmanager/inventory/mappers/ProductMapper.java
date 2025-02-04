package br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ProductRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ProductResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();

        product.setName(dto.name());
        product.setBrand(dto.brand());
        product.setCost(dto.cost());
        product.setAvailableQuantity(dto.availableQuantity());
        product.setTotalQuantity(dto.totalQuantity());
        product.setSupplier(dto.supplierId());
        product.setReceiving(dto.receivingId());

        return product;
    }

    public ProductResponseDTO toProductResponseDTO(Product product) {

        return new ProductResponseDTO(
                product.getName(),
                product.getBrand(),
                product.getCost(),
                product.getAvailableQuantity(),
                product.getTotalQuantity(),
                product.getSupplier(),
                product.getReceiving()
        );
    }
}
