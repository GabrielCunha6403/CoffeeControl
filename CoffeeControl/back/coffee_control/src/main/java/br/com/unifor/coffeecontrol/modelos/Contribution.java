package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "contributions")
@NoArgsConstructor
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    @ManyToOne
    private Solicitation solicitation;
    @Getter @Setter
    @ManyToOne
    private Employee employee;
    @Getter @Setter
    private LocalDate contribution_date;
    @Getter @Setter
    @ManyToMany
    @JoinTable(name="contributions_products", joinColumns=
            {@JoinColumn(name="id_contribution")}, inverseJoinColumns=
            {@JoinColumn(name="id_product")})
    private List<Product> products;
}
