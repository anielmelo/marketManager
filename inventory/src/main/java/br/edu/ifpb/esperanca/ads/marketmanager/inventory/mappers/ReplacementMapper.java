package br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReplacementDetailResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReplacementRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Product;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Replacement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReplacementMapper {

    @Autowired
    private ProductMapper productMapper;

    public Replacement toEntity(ReplacementRequestDTO dto, String idStockKeeper, Product product) {
        Replacement replacement = new Replacement();

        replacement.setDate(LocalDateTime.now());
        replacement.setIdStockKeeper(idStockKeeper);
        replacement.setPurchaseValue(dto.totalPurchaseValue());
        replacement.setQuantityReceived(dto.quantityReceived());
        replacement.setProducts(product);

        return replacement;
    }

    public ReplacementDetailResponseDTO toReplacementDetailResponseDTO(Replacement replacement) {
        Product product = replacement.getProducts();

        return new ReplacementDetailResponseDTO(
                replacement.getId(),
                replacement.getDate(),
                replacement.getPurchaseValue(),
                replacement.getQuantityReceived(),
                productMapper.toProductBasicResponseDTO(product)
        );
    }
}
