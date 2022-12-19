package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Inventory;
import br.com.unifor.coffeecontrol.modelos.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class InventoryDto {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private Integer qnt_now;
    @Getter @Setter
    private Integer qnt_min;

    public InventoryDto(Inventory inventory) {
        this.id = inventory.getId();
        this.qnt_now = inventory.getQnt_now();
        this.qnt_min = inventory.getQnt_min();
    }
    public static Page<InventoryDto> convert(Page<Inventory> inventories) {
        return inventories.map(InventoryDto::new);
    }
}
