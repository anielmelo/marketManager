package br.edu.ifpb.esperanca.ads.marketmanager.sale.services;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.*;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers.SaleMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers.SaleProductMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Discount;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Sale;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.SaleProduct;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories.DiscountRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories.SaleProductRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories.SaleRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale.DuplicateProductException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SaleProductService {
    private final SaleRepository saleRepository;
    private final SaleProductRepository saleProductRepository;
    private final DiscountService discountService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final SaleProductMapper saleProductMapper;
    private final SaleMapper saleMapper;
    private final DiscountRepository discountRepository;

    @Value("${app.url_inventory}")
    private String url_inventory;

    @Autowired
    public SaleProductService(SaleRepository saleRepository, SaleProductRepository saleProductRepository,
            SaleProductMapper saleProductMapper, SaleMapper saleMapper,
            DiscountService discountService, DiscountRepository discountRepository) {
        this.saleRepository = saleRepository;
        this.saleProductRepository = saleProductRepository;
        this.saleProductMapper = saleProductMapper;
        this.saleMapper = saleMapper;
        this.discountService = discountService;
        this.discountRepository = discountRepository;

    }

    @Transactional
    public SaleResponseDTO saveProduct(List<SaleProductRequestDTO> saleProductRequestDTOS, Long discountId) {
        if (saleProductRequestDTOS == null || saleProductRequestDTOS.isEmpty()) {
            throw new EmptyProductListException();
        }

        boolean hasInvalidProduct = saleProductRequestDTOS.stream()
                .anyMatch(p -> p.productId() == null || p.productId() <= 0);

        if (hasInvalidProduct) {
            throw new InvalidProductIdException("Um ou mais produtos possuem ID inválido.");
        }

        if (saleProductRequestDTOS.stream().anyMatch(p -> p.quantity() <= 0)) {
            throw new InvalidProductQuantityException();
        }

        Set<Long> productIds = new HashSet<>();
        for (SaleProductRequestDTO product : saleProductRequestDTOS) {
            if (!productIds.add(product.productId())) {
                throw new DuplicateProductException(
                        "O produto com ID " + product.productId() + " foi adicionado mais de uma vez.");
            }
        }

        for (SaleProductRequestDTO saleProductRequestDTO : saleProductRequestDTOS) {
            ProductResponseDTO productFromInventory = getProductFromInventory(saleProductRequestDTO.productId());

            if (saleProductRequestDTO.quantity() > productFromInventory.availableQuantity()) {
                throw new InsufficientStockException("Produto ID " + saleProductRequestDTO.productId() +
                        " tem apenas " + productFromInventory.availableQuantity() + " disponíveis no estoque.");
            }
        }

        Sale sale = new Sale();
        sale.setDate(LocalDateTime.now());

        if (discountId != null) {
            Discount discount = discountRepository.findById(discountId).orElse(null);

            sale.setDiscount(discount);
        } else {
            sale.setDiscount(null); 
        }

        saleRepository.save(sale);

        for (SaleProductRequestDTO saleProductRequestDTO : saleProductRequestDTOS) {
            ProductResponseDTO productFromInventory = getProductFromInventory(saleProductRequestDTO.productId());

            Double total = productFromInventory.cost() * saleProductRequestDTO.quantity();

            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setProductId(productFromInventory.id());
            saleProduct.setQuantity(saleProductRequestDTO.quantity());
            saleProduct.setTotal(total);
            saleProduct.setSale(sale);

            saleProductRepository.save(saleProduct);
            sale.getProducts().add(saleProduct);
        }

        double totalSemDesconto = sale.calcularTotal();
        double totalComDesconto = discountService
                .applyDiscount(sale.getDiscount() != null ? sale.getDiscount().getId() : null, totalSemDesconto);

        sale.setTotal(totalComDesconto);
        saleRepository.save(sale);

        return saleMapper.toSaleResponseDTO(sale);
    }

    public void deleteProduct(Long saleProductId) {
        if (!saleProductRepository.existsById(saleProductId)) {
            throw new SaleProductNotFoundException(saleProductId);
        }
        saleProductRepository.deleteById(saleProductId);

    }

    public SaleProductResponseDTO getSaleProductById(Long id) {
        SaleProduct saleProduct = saleProductRepository.findById(id)
                .orElseThrow(() -> new SaleProductNotFoundException(id));
        return saleProductMapper.toSaleProductResponseDTO(saleProduct);
    }

    public List<SaleProductResponseDTO> getAllSaleProducts() {
        return saleProductRepository.findAll()
                .stream()
                .map(saleProductMapper::toSaleProductResponseDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getProductFromInventory(Long productId) {
        String url = url_inventory + productId;

        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> body = response.getBody();

                return new ProductResponseDTO(
                        ((Number) body.get("id")).longValue(),
                        (String) body.get("name"),
                        (String) body.get("brand"),
                        ((Number) body.get("cost")).doubleValue(),
                        ((Number) body.get("availableQuantity")).intValue());
            } else {
                throw new ExternalApiException("Produto com ID " + productId + " não encontrado no inventário.");
            }

        } catch (HttpClientErrorException.NotFound e) {
            throw new ExternalApiException("Produto com ID " + productId + " não encontrado na API de inventário.");
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao buscar produto na API de inventário: " + e.getMessage());
        }
    }
}
