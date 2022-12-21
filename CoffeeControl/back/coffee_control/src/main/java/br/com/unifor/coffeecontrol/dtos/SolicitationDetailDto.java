package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Contribution;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.modelos.SolicitationsProducts;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SolicitationDetailDto {
    private int id;
    private String name;
    private String nameEmployee;
    private LocalDate date;
    private Boolean enable;
    private List<ContributionSubDetailDto> contributions = new ArrayList<ContributionSubDetailDto>();
    private List<ProductDto> products = new ArrayList<ProductDto>();

    public SolicitationDetailDto(Solicitation solicitation) {
        this.id = solicitation.getId();
        this.name = solicitation.getName();
        this.nameEmployee = solicitation.getEmployee().getName();
        this.date = solicitation.getDate();
        solicitation.getContributions().forEach(contribution -> {
            this.contributions.add(new ContributionSubDetailDto(contribution));
        });
        solicitation.getProducts().forEach(product -> {
            this.products.add(new ProductDto(product.getProduct()));
        });
    }

    public static Page<SolicitationDetailDto> convert(Page<Solicitation> topicos) {
        return topicos.map(SolicitationDetailDto::new);
    }
}
