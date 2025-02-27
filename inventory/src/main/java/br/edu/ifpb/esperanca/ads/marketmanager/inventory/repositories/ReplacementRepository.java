package br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Replacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplacementRepository extends JpaRepository<Replacement, Long> {
}
