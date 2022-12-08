package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "solicitations")
@NoArgsConstructor
public class Solicitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    @OneToMany(mappedBy = "solicitation")
    private List<Contribution> contributions;
    @Getter @Setter
    @ManyToMany
    @JoinTable(name="solicitations_products", joinColumns=
            {@JoinColumn(name="id_solicitations")}, inverseJoinColumns=
            {@JoinColumn(name="id_product")})
    private List<Product> products;

    public Solicitation(String name, Employee employee) {
        this.name = name;
        this.date = LocalDate.now();
        this.employee = employee;
    }
}
