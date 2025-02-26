package br.edu.ifpb.esperanca.ads.marketmanager.sale.services;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.dtos.SaleResponseDTO;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.mappers.SaleMapper;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Sale;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories.SaleRepository;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale.EmptySaleListException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.sale.SaleNotFoundException;
import br.edu.ifpb.esperanca.ads.marketmanager.sale.services.exceptions.saleproduct.SaleProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    @Autowired
    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    public SaleResponseDTO getSaleById(Long id) {
        Sale sale = this.saleRepository.findById(id).orElseThrow(() -> new SaleNotFoundException(id));
        return saleMapper.toSaleResponseDTO(sale);
    }

    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll()
                .stream().map(saleMapper::toSaleResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteSale(Long id) {
        if (!saleRepository.existsById(id)) {
            throw new SaleProductNotFoundException(id);
        }
        saleRepository.deleteById(id);
    }

    public void deleteAllSales() {
        if (saleRepository.count() == 0) {
            throw new EmptySaleListException();
        }
        saleRepository.deleteAll();
    }


}
