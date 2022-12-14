package br.com.unifor.coffeecontrol.modelos;

import br.com.unifor.coffeecontrol.modelos.IdClasses.ContributionsProductsId;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "contributions_products")
public class ContributionsProducts {
    @EmbeddedId
    private ContributionsProductsId id;
    @ManyToOne
    @MapsId("id_contribution")
    @JoinColumn(name = "id_contribution")
    private Contribution contribution;
    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;
    private int quantity;

    public ContributionsProducts(ContributionsProductsId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
