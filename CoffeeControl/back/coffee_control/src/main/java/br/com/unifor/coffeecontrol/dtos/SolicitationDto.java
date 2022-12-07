package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Solicitation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public class SolicitationDto {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String nameEmployee;
    @Getter @Setter
    private LocalDate date;

    public SolicitationDto(Solicitation solicitation) {
        this.id = solicitation.getId();
        this.name = solicitation.getName();
        this.nameEmployee = solicitation.getEmployee().getName();
        this.date = solicitation.getDate();
    }

    public static Page<SolicitationDto> convert(Page<Solicitation> topicos) {
        return topicos.map(SolicitationDto::new);
    }
}
