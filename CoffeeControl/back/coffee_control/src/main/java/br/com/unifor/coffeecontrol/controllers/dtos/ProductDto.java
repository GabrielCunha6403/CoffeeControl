package br.com.unifor.coffeecontrol.controllers.dtos;

import br.com.unifor.coffeecontrol.modelos.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class ProductDto {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String description;
    @Getter
    private int qnt_min_employee;
    @Getter
    private Boolean enable;
    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.qnt_min_employee = product.getQnt_min_employee();
        this.enable = product.getEnable();
    }

    public static Page<ProductDto> convert(Page<Product> topicos) {
        return topicos.map(ProductDto::new);
    }
}
