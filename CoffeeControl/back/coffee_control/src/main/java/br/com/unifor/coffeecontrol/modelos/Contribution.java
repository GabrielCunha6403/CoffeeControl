package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @JoinColumn(name = "id_solicitation")
    private Solicitation solicitation;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
    @Getter @Setter
    private LocalDate date;
//    @Getter @Setter
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name="contributions_products", joinColumns=
//            {@JoinColumn(name="id_contribution")}, inverseJoinColumns=
//            {@JoinColumn(name="id_product")})
//    private List<Product> products;
    @Getter @Setter
    @OneToMany(mappedBy = "contribution")
    private List<ContributionsProducts> products;

    public Contribution(LocalDate date, Employee employee, Solicitation solicitation) {
        this.setDate(date);
        this.setEmployee(employee);
        this.setSolicitation(solicitation);
    }
}
