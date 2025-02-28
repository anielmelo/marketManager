package br.edu.ifpb.esperanca.ads.marketmanager.sale.services;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.DiscountRequestDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.DiscountResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers.DiscountMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Discount;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories.DiscountRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount.DiscountNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount.EmptyDiscountListException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount.InvalidDiscountException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.discount.InvalidMinimumValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Autowired
    public DiscountService(DiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    @Transactional
    public DiscountResponseDTO saveDiscount(DiscountRequestDTO discountRequestDTO) {
        if (discountRequestDTO == null) {
            throw new InvalidDiscountException();
        }
        if (discountRequestDTO.value() <= 0) {
            throw new InvalidDiscountException();
        }
        if (discountRequestDTO.minimumValue() <= 0) {
            throw new InvalidMinimumValueException();
        }
        Discount discount = discountMapper.toEntity(discountRequestDTO);
        discount.setActive(true); // Definir como ativo ao criar
        discount = discountRepository.save(discount);
        return discountMapper.toDiscountResponseDTO(discount);
    }

    @Transactional
    public DiscountResponseDTO updateDiscount(Long id, DiscountRequestDTO discountRequestDTO) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
        if (discountRequestDTO.value() <= 0) {
            throw new InvalidDiscountException();
        }
        if (discountRequestDTO.minimumValue() <= 0) {
            throw new InvalidMinimumValueException(); // Verificação do minimumValue
        }
        discount.setCode(discountRequestDTO.code());
        discount.setDescription(discountRequestDTO.description());
        discount.setValue(discountRequestDTO.value());
        discount.setMinimumValue(discountRequestDTO.minimumValue());
        discount = discountRepository.save(discount);
        return discountMapper.toDiscountResponseDTO(discount);
    }

    @Transactional
    public void disableDiscount(Long id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
        discount.setActive(false);
        discountRepository.save(discount);
    }

    public DiscountResponseDTO getDiscountById(Long id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
        return discountMapper.toDiscountResponseDTO(discount);
    }

    public List<DiscountResponseDTO> getAllDiscounts() {
        List<Discount> discounts = discountRepository.findAll();
        if (discounts.isEmpty()) {
            throw new EmptyDiscountListException();
        }
        return discounts.stream()
                .map(discountMapper::toDiscountResponseDTO)
                .collect(Collectors.toList());
    }

    public Double applyDiscount(Long id, Double totalValue) {
        if (id == null) {
            return totalValue; // Se o id for null, retorna o valor total sem desconto
        }
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
        if (!discount.getActive()) {
            throw new InvalidDiscountException();
        }
        if (totalValue < discount.getMinimumValue()) {
            throw new InvalidDiscountException();
        }
        // Aplica o desconto
        Double discountAmount = (discount.getValue() / 100) * totalValue;
        Double finalValue = totalValue - discountAmount;

        return Math.max(finalValue, 0); // Evita valores negativos
    }

}
