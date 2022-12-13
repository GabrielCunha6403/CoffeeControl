package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.IdClasses.SolicitationsProductsId;
import br.com.unifor.coffeecontrol.modelos.SolicitationsProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationsProductsRepository extends JpaRepository<SolicitationsProducts, SolicitationsProductsId> {
}
