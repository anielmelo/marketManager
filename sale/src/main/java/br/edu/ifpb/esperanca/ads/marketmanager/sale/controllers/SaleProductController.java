package br.edu.ifpb.esperanca.ads.marketmanager.sale.controllers;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleProductRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleProductResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.SaleProductService;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleProductController {

    private final SaleProductService saleProductService;
    private  final SaleService saleService;

    public SaleProductController(SaleProductService saleProductService,  SaleService saleService) {
        this.saleProductService = saleProductService;
        this.saleService = saleService;
    }

    @PostMapping({"/add", "/add/{id}"})
    public ResponseEntity<Void> addProduct(@RequestBody List<SaleProductRequestDTO> products, @PathVariable(required = false)  Long discountId) {
        for (SaleProductRequestDTO product : products) {
            saleProductService.getProductFromInventory(product.productId());
        }

        saleProductService.saveProduct(products, discountId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/products")
    public ResponseEntity<List<SaleProductResponseDTO>> getAllSaleProducts() {
        return ResponseEntity.ok(saleProductService.getAllSaleProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<SaleProductResponseDTO> getSaleProductById(@PathVariable Long id) {
        return ResponseEntity.ok(saleProductService.getSaleProductById(id));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        saleProductService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
