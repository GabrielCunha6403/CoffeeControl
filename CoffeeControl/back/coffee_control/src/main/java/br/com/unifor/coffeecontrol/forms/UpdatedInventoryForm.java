package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Inventory;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.InventoryRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

public class UpdatedInventoryForm {
    @Getter @Setter
    private Integer qnt_now;
    @Getter @Setter
    private Integer qnt_min;

    public Inventory update(int id, InventoryRepository repository){
        Inventory inventory = repository.getReferenceById(id);
        inventory.setQnt_now(this.qnt_now);
        inventory.setQnt_min(this.qnt_min);
        return inventory;
    }
}
