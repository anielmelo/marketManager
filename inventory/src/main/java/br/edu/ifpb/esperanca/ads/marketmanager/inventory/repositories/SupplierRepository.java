package br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
