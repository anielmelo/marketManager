package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.SupplierRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.SupplierResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers.SupplierMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Supplier;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories.SupplierRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.supplier.SupplierAlreadyExistsException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.supplier.SupplierNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    private static final Logger log = LoggerFactory.getLogger(SupplierService.class);
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public SupplierResponseDTO createSupplier(SupplierRequestDTO dto) {
        log.info("Creating new supplier: {}", dto.name());

        if (supplierRepository.existsByCnpj(dto.cnpj())) {
            log.error("Supplier with CNPJ {} already exists", dto.cnpj());
            throw new SupplierAlreadyExistsException();
        }

        Supplier supplier = supplierMapper.toEntity(dto);
        supplier = supplierRepository.save(supplier);
        log.info("Supplier created successfully: {}", supplier.getId());

        return supplierMapper.toDTO(supplier);
    }

    public SupplierResponseDTO getSupplierById(Long id) {
        log.info("Fetching supplier with ID: {}", id);
        var supplier = findSupplierEntity(id);
        return supplierMapper.toDTO(supplier);
    }

    public List<SupplierResponseDTO> getAllSuppliers() {
        log.info("Fetching all suppliers");
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO dto) {
        log.info("Updating supplier with ID: {}", id);
        var supplier = findSupplierEntity(id);

        supplier.setName(dto.name());
        supplier.setCnpj(dto.cnpj());
        supplier.setAddress(dto.address());

        supplier = supplierRepository.save(supplier);
        log.info("Supplier updated successfully: {}", supplier.getId());

        return supplierMapper.toDTO(supplier);
    }

    public void deleteSupplier(Long id) {
        log.info("Deleting supplier with ID: {}", id);
        Supplier supplier = findSupplierEntity(id);

        supplierRepository.delete(supplier);
        log.info("Supplier deleted successfully");
    }

    protected Supplier findSupplierEntity(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Supplier Entity not found with ID: {}", id);
                    return new SupplierNotFoundException();
                });
    }
}
