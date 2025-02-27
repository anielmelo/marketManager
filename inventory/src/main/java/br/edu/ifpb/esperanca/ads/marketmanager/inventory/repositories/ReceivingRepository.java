package br.edu.ifpb.esperanca.ads.marketmanager.inventory.repositories;

import br.edu.ifpb.esperanca.ads.marketmanager.inventory.models.Receiving;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivingRepository extends JpaRepository<Receiving, Long> { }
