package br.com.unifor.coffeecontrol.modelos.IdClasses;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class SolicitationsProductsId implements Serializable {
    private int id_solicitation;
    private int id_product;

    public SolicitationsProductsId(int id_solicitation, int id_product) {
        super();
        this.id_solicitation = id_solicitation;
        this.id_product = id_product;
    }
}
