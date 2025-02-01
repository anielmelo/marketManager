package br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
