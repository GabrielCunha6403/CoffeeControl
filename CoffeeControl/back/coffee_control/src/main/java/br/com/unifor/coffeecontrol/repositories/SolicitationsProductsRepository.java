package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.IdClasses.SolicitationsProductsId;
import br.com.unifor.coffeecontrol.modelos.SolicitationsProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitationsProductsRepository extends JpaRepository<SolicitationsProducts, SolicitationsProductsId> {
    @Query(value = "SELECT EXISTS (SELECT 1 FROM solicitations_products " +
            "WHERE solicitations_products.id_solicitation = :id_solicitation " +
            "AND solicitations_products.id_product = :id_product)",
    nativeQuery = true)
    Boolean findProductInSolicitation(Integer id_solicitation, Integer id_product);
}
