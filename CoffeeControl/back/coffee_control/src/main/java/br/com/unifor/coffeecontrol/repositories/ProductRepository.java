package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);

    @Query(value = "select * from products p\n" +
            "\twhere (lower(p.name) like  lower(concat('%', :name, '%')) or :name is null) \n" +
            "\tand (lower(p.description) like  lower(concat('%', :description, '%')) or :description is null) \n" +
            "\tand (p.qnt_min_inventory = :qnt_min_inventory or :qnt_min_inventory is null) \n" +
            "\tand (p.enable = :enable or :enable is null)", nativeQuery = true)
    List<Object> genericFilterProduct(String name, String description, Integer qnt_min_inventory, Boolean enable);

}
