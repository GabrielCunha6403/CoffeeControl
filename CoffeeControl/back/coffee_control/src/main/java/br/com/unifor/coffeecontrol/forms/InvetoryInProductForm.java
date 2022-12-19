package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Inventory;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

public class InvetoryInProductForm {
    @Getter @Setter
    private Integer qnt_now;
    @Getter @Setter
    private Integer qnt_min;
    public Inventory convert(){
        return new Inventory(this.qnt_now, this.qnt_min);
    }
}
