package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    private int qnt_min_employee;
    @Getter @Setter
    private Boolean enable;
    @Getter @Setter
    @ManyToMany
    @JoinTable(name="contributions_products", joinColumns=
            {@JoinColumn(name="id_contributions")}, inverseJoinColumns=
            {@JoinColumn(name="id_product")})
    private List<Contribution> contributions = new ArrayList<>();
    @Getter @Setter
    @ManyToMany
    @JoinTable(name="solicitations_products", joinColumns=
            {@JoinColumn(name="id_solicitations")}, inverseJoinColumns=
            {@JoinColumn(name="id_product")})
    private List<Solicitation> solicitations = new ArrayList<>();

    public Product(String name, String description, int qnt_min_employee, Boolean enable) {
        this.name = name;
        this.description = description;
        this.qnt_min_employee = qnt_min_employee;
        this.enable = enable;
    }
}
