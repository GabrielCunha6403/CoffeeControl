package br.com.unifor.coffeecontrol.controllers.forms;

import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class UpdatedSolicitationForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private LocalDate date;

    public Solicitation update(int id, SolicitationRepository repository){
        Solicitation solicitation = repository.getReferenceById(id);
        solicitation.setName(this.getName());
        solicitation.setDate(this.getDate());

        return solicitation;
    }
}
