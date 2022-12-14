package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

public class UpdatedProductForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private int qnt_min_inventory;
    @Getter @Setter
    private Boolean enable;

    public UpdatedProductForm(int count) {

    }

    public Product update(int id, ProductRepository repository){
        Product product = repository.getReferenceById(id);
        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setEnable(this.getEnable());

        return product;
    }
}
