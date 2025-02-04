package br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.SupplierRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.SupplierResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequestDTO dto) {
        return new Supplier(null, dto.name(), dto.cnpj(), dto.address());
    }

    public SupplierResponseDTO toDTO(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getCnpj(),
                supplier.getAddress());
    }
}
