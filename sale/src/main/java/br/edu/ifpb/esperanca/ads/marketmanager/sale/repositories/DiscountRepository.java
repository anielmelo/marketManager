package br.edu.ifpb.esperanca.ads.marketmanager.sale.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.sale.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
}
