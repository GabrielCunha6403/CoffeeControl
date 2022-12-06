package br.com.unifor.coffeecontrol.controllers.forms;

import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

public class ProductForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private int qnt_min_employee;
    @Getter @Setter
    private Boolean enable;

    public Product convert(ProductRepository productRepository){
        return new Product(this.name, this.description, this.qnt_min_employee, this.enable);
    }
}
