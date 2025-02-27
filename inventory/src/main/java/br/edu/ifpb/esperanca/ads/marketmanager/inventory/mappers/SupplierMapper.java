package br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.SupplierRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.SupplierResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequestDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.name());
        supplier.setCnpj(dto.cnpj());
        supplier.setAddress(dto.address());
        supplier.setContact(dto.contact());
        return supplier;
    }

    public SupplierResponseDTO toDTO(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getCnpj(),
                supplier.getAddress(),
                supplier.getContact());
    }
}
