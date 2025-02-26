package br.edu.ifpb.esperanca.ads.marketmanager.sale.controllers;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.DiscountRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.DiscountResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales/discount")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public ResponseEntity<DiscountRequestDTO> addDiscount(@RequestBody DiscountRequestDTO dto) {
        discountService.saveDiscount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscountRequestDTO> updateDiscount(@PathVariable Long id, @RequestBody DiscountRequestDTO dto) {
        discountService.updateDiscount(id, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Long id) {
        discountService.disableDiscount(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<Void> disableDiscount(@PathVariable Long id) {
        discountService.disableDiscount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountResponseDTO> getDiscountById(@PathVariable Long id) {
        return ResponseEntity.ok(discountService.getDiscountById(id));

    }

    @GetMapping
    public ResponseEntity<List<DiscountResponseDTO>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }
}
