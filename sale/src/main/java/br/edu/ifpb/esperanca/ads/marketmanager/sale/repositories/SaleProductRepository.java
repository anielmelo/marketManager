package br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleProductRepository  extends JpaRepository<SaleProduct, Long> {
}
