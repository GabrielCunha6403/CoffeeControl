package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.ContributionsProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContributionsProductsRepository extends JpaRepository<ContributionsProducts, Integer> {

}
