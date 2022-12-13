package br.com.unifor.coffeecontrol.modelos;

import br.com.unifor.coffeecontrol.modelos.IdClasses.ContributionsProductsId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity(name = "contributions_products")
public class ContributionsProducts {
    @EmbeddedId
    private ContributionsProductsId id;
    @ManyToOne
    @MapsId("id_contribution")
    private Contribution contribution;
    @ManyToOne
    @MapsId("id_product")
    private Product product;
    private int quantity;

    public ContributionsProducts(ContributionsProductsId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
