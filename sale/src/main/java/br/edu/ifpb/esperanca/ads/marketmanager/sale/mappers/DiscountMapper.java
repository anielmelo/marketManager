package br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.DiscountRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.DiscountResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Discount;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {
    public Discount toEntity(DiscountRequestDTO dto, String idSaleKeeper) {
        Discount discount = new Discount();

        discount.setCode(dto.code());
        discount.setIdSaleKeeper(idSaleKeeper);
        discount.setDescription(dto.description());
        discount.setValue(dto.value());
        discount.setMinimumValue(dto.minimumValue());

        return discount;
    }

    public DiscountResponseDTO toDiscountResponseDTO(Discount discount) {
        return new DiscountResponseDTO(
                discount.getId(),
                discount.getIdSaleKeeper(),
                discount.getCode(),
                discount.getDescription(),
                discount.getValue(),
                discount.getMinimumValue(),
                discount.getActive()
        );
    }
}
