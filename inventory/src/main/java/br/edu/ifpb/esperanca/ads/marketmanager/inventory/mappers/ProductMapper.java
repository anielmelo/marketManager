package br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.*;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Product;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Receiving;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto, Supplier supplier, Receiving receiving) {
        Product product = new Product();

        product.setName(dto.name());
        product.setBrand(dto.brand());
        product.setCost(dto.cost());
        product.setAvailableQuantity(dto.availableQuantity());
        product.setSupplier(supplier);
        product.setReceiving(receiving);

        return product;
    }

    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCost(),
                product.getAvailableQuantity(),
                new SupplierResponseDTO(product.getSupplier().getId(), product.getSupplier().getName(), product.getSupplier().getCnpj(), product.getSupplier().getAddress()),
                new ReceivingResponseDTO(product.getReceiving().getId(), product.getReceiving().getDate())
        );
    }
}
