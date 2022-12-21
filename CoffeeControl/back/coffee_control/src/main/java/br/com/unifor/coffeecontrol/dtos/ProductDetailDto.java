package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Inventory;
import br.com.unifor.coffeecontrol.modelos.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

public class ProductDetailDto {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Boolean enable;
    @Getter
    private Inventory inventory;
    public ProductDetailDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.enable = product.getEnable();
        this.inventory = product.getInventory();
    }

    public static Page<ProductDetailDto> convert(Page<Product> topicos) {
        return topicos.map(ProductDetailDto::new);
    }
}
