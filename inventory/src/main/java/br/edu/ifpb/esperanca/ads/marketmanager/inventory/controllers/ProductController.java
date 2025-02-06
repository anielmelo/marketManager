package br.edu.ifpb.esperanca.ads.marketmanager.inventory.controllers;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ProductRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.dtos.ProductResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.inventory.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> registerProduct(@Valid @RequestBody ProductRequestDTO product) {
        log.info("Registering new product: {}", product.name());
        var obj = productService.registerNewProduct(product);
        log.info("Product registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        log.info("Fetching product with ID: {}", id);
        var obj = productService.getProductById(id);
        log.info("Product fetched successfully: {}", obj);
        return ResponseEntity.ok(obj);
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        log.info("Fetching all products");
        var obj = productService.getAllProducts();
        log.info("Total products found: {}", obj.size());
        return ResponseEntity.ok(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProductById(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        log.info("Updating product with ID: {}", id);
        var obj = productService.updateProduct(id, dto);
        log.info("Product updated successfully: {}", obj);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        log.info("Product deleted successfully");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestBody Integer quantity) {
        log.info("Updating stock for product ID: {} with quantity: {}", id, quantity);
        productService.updateStockQuantity(id, quantity);
        log.info("Stock updated successfully for product ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
