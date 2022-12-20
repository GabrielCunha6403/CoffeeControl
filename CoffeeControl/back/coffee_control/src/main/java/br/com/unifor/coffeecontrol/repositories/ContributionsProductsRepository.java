package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.ContributionsProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionsProductsRepository extends JpaRepository<ContributionsProducts, Integer> {

    @Query(value = "select sum(quantity) from contributions_products where id_product = :id", nativeQuery = true)
    Integer getReceivedAmountOfOneProductById(Integer id);
}
