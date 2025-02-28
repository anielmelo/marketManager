package br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.*;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Product;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Replacement;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequestDTO dto, Supplier supplier, List<Replacement> replacements) {
        Product product = new Product();

        product.setName(dto.name());
        product.setBrand(dto.brand());
        product.setCost(dto.cost());
        product.setAvailableQuantity(dto.availableQuantity());
        product.setSupplier(supplier);
        product.setReplacement(replacements);

        return product;
    }

    public ProductResponseDTO toProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCost(),
                product.getAvailableQuantity(),
                new SupplierResponseDTO(
                        product.getSupplier().getId(),
                        product.getSupplier().getName(),
                        product.getSupplier().getCnpj(),
                        product.getSupplier().getAddress(),
                        product.getSupplier().getContact()),
                product.getReplacement()
                        .stream()
                        .map(
                                r -> new ReplacementResponseDTO(
                                r.getId(), r.getIdStockKeeper(), r.getDate(),
                                r.getPurchaseValue(), r.getQuantityReceived()
                                )).toList()
        );
    }

    public ProductBasicResponseDTO toProductBasicResponseDTO(Product product) {
        return new ProductBasicResponseDTO(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCost(),
                product.getAvailableQuantity(),
                new SupplierResponseDTO(
                        product.getSupplier().getId(),
                        product.getSupplier().getName(),
                        product.getSupplier().getCnpj(),
                        product.getSupplier().getAddress(),
                        product.getSupplier().getContact())
        );
    }
}
