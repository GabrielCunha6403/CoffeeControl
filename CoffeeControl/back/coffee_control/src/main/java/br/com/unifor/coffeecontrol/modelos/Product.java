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
    @ManyToMany(mappedBy = "products")
    private List<Contribution> contributions;
    @Getter @Setter
    @ManyToMany(mappedBy = "products")
    private List<Solicitation> solicitations;

    public Product(String name, String description, int qnt_min_employee, Boolean enable) {
        this.name = name;
        this.description = description;
        this.qnt_min_employee = qnt_min_employee;
        this.enable = enable;
    }
}
