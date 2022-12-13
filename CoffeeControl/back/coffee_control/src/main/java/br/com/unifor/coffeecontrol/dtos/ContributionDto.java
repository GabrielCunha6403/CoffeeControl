package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Contribution;
import br.com.unifor.coffeecontrol.modelos.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class ContributionDto {

    @Getter
    private int id;
    @Getter
    private int id_solicitation;
    @Getter
    private int id_employee;
    @Getter
    private LocalDate date;

    public ContributionDto(Contribution contribution){
        this.id = contribution.getId();
        this.id_solicitation = contribution.getSolicitation().getId();
        this.id_employee = contribution.getEmployee().getId();
        this.date = contribution.getDate();
    }
    public static Page<ContributionDto> convert(Page<Contribution> contributions) {
        return contributions.map(ContributionDto::new);
    }
}
