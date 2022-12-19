package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Product;
import lombok.Getter;
import org.springframework.data.domain.Page;

public class ProductDto {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private Boolean enable;
    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.enable = product.getEnable();
    }

    public static Page<ProductDto> convert(Page<Product> topicos) {
        return topicos.map(ProductDto::new);
    }
}
