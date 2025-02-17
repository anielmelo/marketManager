package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReplacementDetailResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ReplacementRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers.ReplacementMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Product;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Replacement;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories.ReplacementRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.replacement.ReplacementNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplacementService {
    private static final Logger log = LoggerFactory.getLogger(ReplacementService.class);
    private final ReplacementRepository replacementRepository;
    private final ReplacementMapper replacementMapper;
    private final ProductService productService;

    @Autowired
    public ReplacementService(ReplacementRepository replacementRepository, ReplacementMapper replacementMapper, ProductService productService) {
        this.replacementRepository = replacementRepository;
        this.replacementMapper = replacementMapper;
        this.productService = productService;
    }

    @Transactional
    public ReplacementDetailResponseDTO registerNewReplacement(ReplacementRequestDTO dto) {
        log.info("Registering new replacement for product ID: {}", dto.productId());

        Product product = productService.findProductById(dto.productId());
        Replacement replacement = replacementMapper.toEntity(dto, product);

        productService.increaseAvailableQuantity(dto.productId(), replacement.getQuantityReceived());
        replacement = replacementRepository.save(replacement);


        log.info("Replacement registered successfully with ID: {}", replacement.getId());

        return replacementMapper.toReplacementDetailResponseDTO(replacement);
    }

    public ReplacementDetailResponseDTO getReplacementById(Long id) {
        log.info("Fetching replacement by ID: {}", id);

        Replacement replacement = findReplacementById(id);

        log.info("Replacement found: ID: {}", replacement.getId());
        return replacementMapper.toReplacementDetailResponseDTO(replacement);
    }

    public List<ReplacementDetailResponseDTO> getAllReplacements() {
        log.info("Fetching all replacements");
        var replacements = replacementRepository.findAll().stream()
                .map(replacementMapper::toReplacementDetailResponseDTO)
                .toList();
        log.info("Total replacements found: {}", replacements.size());
        return replacements;
    }

    public ReplacementDetailResponseDTO updateReplacement(Long id, ReplacementRequestDTO dto) {
        log.info("Updating replacement with ID: {}", id);
        Replacement replacement = findReplacementById(id);

        replacement.setQuantityReceived(dto.quantityReceived());
        replacement.setPurchaseValue(dto.totalPurchaseValue());

        replacement = replacementRepository.save(replacement);
        log.info("Replacement updated successfully with ID: {}", replacement.getId());
        return replacementMapper.toReplacementDetailResponseDTO(replacement);
    }

    public void deleteReplacement(Long id) {
        log.info("Deleting replacement with ID: {}", id);

        replacementRepository.delete(findReplacementById(id));

        log.info("Replacement with ID {} deleted successfully", id);
    }

    protected Replacement findReplacementById(Long id) {
        return replacementRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Replacement with ID {} not found", id);
                    return new ReplacementNotFoundException();
                });
    }

    protected List<Replacement> findAllReplacements(Long productId) {
        return productService.findProductById(productId).getReplacement();
    }

}
