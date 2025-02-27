package br.edu.ifpb.esperanca.ads.marketmanager.inventory.services;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ProductRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ProductResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.mappers.ProductMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Product;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories.ProductRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product.InsufficientQuantityException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product.InvalidProductQuantityException;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.exceptions.product.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SupplierService supplierService;
    private final ReplacementService replacementService;
    private final static Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, SupplierService supplierService, @Lazy ReplacementService replacementService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.supplierService = supplierService;
        this.replacementService = replacementService;
        this.productMapper = productMapper;
    }

    public ProductResponseDTO registerNewProduct(ProductRequestDTO dto) {
        log.info("Registering a new product...");
        validateProduct(dto);

        var supplier = supplierService.findSupplierEntity(dto.supplierId());

        Product product = productMapper.toEntity(dto, supplier, new ArrayList<>());
        log.info("Product successfully registered!");

        productRepository.save(product);
        return productMapper.toProductResponseDTO(product);
    }

    public ProductResponseDTO getProductById(Long id) {
        log.info("Searching for product with id: {}", id);
        var product = findProductById(id);
        log.info("Product found successfully.");
        return productMapper.toProductResponseDTO(product);
    }

    public List<ProductResponseDTO> getAllProducts() {
        log.info("Returning all products from the database.");
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product...");
        var product = findProductById(id);

        log.info("Product successfully deleted.");
        productRepository.delete(product);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        log.info("Updating product with id: {}", id);

        var existingProduct = findProductById(id);

        var supplier = supplierService.findSupplierEntity(dto.supplierId());

        validateProduct(dto);

        Product updatedProduct = productMapper.toEntity(dto, supplier, replacementService.findAllReplacements(id));
        updatedProduct.setId(existingProduct.getId());

        log.info("Product successfully updated!");

        productRepository.save(updatedProduct);
        return productMapper.toProductResponseDTO(updatedProduct);
    }

    public void updateStockQuantity(Long productId, int quantity) {
        log.info("Attempting to update stock for Product ID: {} with quantity: {}", productId, quantity);

        Product product = findProductById(productId);

        log.info("Current stock for Product ID {}: {}", productId, product.getAvailableQuantity());


        if (product.getAvailableQuantity() - quantity < 0) {
            log.error("Insufficient quantity! Available: {}, Requested: {}", product.getAvailableQuantity(), quantity);
            throw new InvalidProductQuantityException();
        }

        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
        productRepository.save(product);

        log.info("Stock updated successfully for Product ID: {}. New stock: {}",
                productId, product.getAvailableQuantity());
    }

    private void validateProduct(ProductRequestDTO dto) {
        if (dto.availableQuantity() < 1) {
            log.warn("Attempt to register a product with quantities less than or equal to 0");
            throw new InsufficientQuantityException();
        }
    }

    protected Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new ProductNotFoundException();
                });
    }

    protected void increaseAvailableQuantity(Long id, int quantity) {
        Product product = findProductById(id);

        product.setAvailableQuantity(product.getAvailableQuantity() + quantity);

        productRepository.save(product);
    }


}