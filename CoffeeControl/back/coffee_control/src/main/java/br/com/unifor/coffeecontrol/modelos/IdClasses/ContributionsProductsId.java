package br.com.unifor.coffeecontrol.modelos.IdClasses;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ContributionsProductsId implements Serializable {
    private int id_contribution;
    private int id_product;
}
