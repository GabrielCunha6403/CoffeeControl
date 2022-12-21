package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Solicitation;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SolicitationSubDetailDto {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private LocalDate date;
    @Getter
    private Boolean enable;
    @Getter
    private Integer num_of_contributions;
    @Getter
    private List<ProductDto> products = new ArrayList<ProductDto>();

    public SolicitationSubDetailDto(Solicitation solicitation) {
        this.id = solicitation.getId();
        this.name = solicitation.getName();
        this.date = solicitation.getDate();
        this.enable = solicitation.getEnable();
//        this.num_of_contributions = solicitation.getContributions().size();
        solicitation.getProducts().forEach(product -> {
            this.products.add(new ProductDto(product.getProduct()));
        });
    }

    public static SolicitationSubDetailDto convert(Solicitation solicitation) {
        return new SolicitationSubDetailDto(solicitation);
    }
}
