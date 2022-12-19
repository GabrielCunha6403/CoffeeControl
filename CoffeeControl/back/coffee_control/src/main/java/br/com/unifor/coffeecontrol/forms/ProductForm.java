package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Inventory;
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
    private Boolean enable;
    @Getter @Setter
    private InvetoryInProductForm inventory;

    public Product convert(){
        return new Product(this.name, this.description, this.enable, this.inventory.convert());
    }
}
