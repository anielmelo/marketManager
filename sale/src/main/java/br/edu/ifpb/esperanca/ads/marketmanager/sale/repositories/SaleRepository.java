package br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
