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

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody List<SaleProductRequestDTO> products, @RequestParam(required = false)  Long discountId) {
        // Validar a quantidade dos produtos e verificar se existem no inventário
        for (SaleProductRequestDTO product : products) {
            // Verifica se o produto existe na API Inventory
            saleProductService.getProductFromInventory(product.productId());
        }
        // Salva os produtos e aplica o desconto
        saleProductService.saveProduct(products, discountId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<SaleProductResponseDTO> getSaleProductById(@PathVariable Long id) {
        return ResponseEntity.ok(saleProductService.getSaleProductById(id));
    }

    @GetMapping("/products")
    public ResponseEntity<List<SaleProductResponseDTO>> getAllSaleProducts() {
        return ResponseEntity.ok(saleProductService.getAllSaleProducts());
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        saleProductService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
