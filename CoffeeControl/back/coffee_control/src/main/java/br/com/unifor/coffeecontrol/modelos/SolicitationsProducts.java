package br.com.unifor.coffeecontrol.modelos;

import br.com.unifor.coffeecontrol.modelos.IdClasses.SolicitationsProductsId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity(name = "solicitations_products")
public class SolicitationsProducts {
    @EmbeddedId
    private SolicitationsProductsId id;
    @ManyToOne
    @MapsId("id_solicitation")
    @JoinColumn(name = "id_solicitation")
    private Solicitation solicitation;
    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;
    private int quantity;

    public SolicitationsProducts(SolicitationsProductsId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
