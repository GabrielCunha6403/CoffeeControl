package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private Boolean enable;
    @Getter @Setter
    @OneToMany(mappedBy = "product")
    private List<ContributionsProducts> contributions;
    @Getter @Setter
    @OneToMany
    private List<SolicitationsProducts> solicitations;
    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "id_inventory")
    private Inventory inventory;

    public Product(String name, String description, Boolean enable, Inventory inventory) {
        this.name = name;
        this.description = description;
        this.enable = enable;
        this.inventory = inventory;
    }

}
