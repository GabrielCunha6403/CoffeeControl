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
@Getter @Setter
public class Contribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_solicitation")
    private Solicitation solicitation;
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
    private LocalDate date;
    @OneToMany(mappedBy = "contribution")
    private List<ContributionsProducts> products;

    public Contribution(LocalDate date, Employee employee, Solicitation solicitation) {
        this.setDate(date);
        this.setEmployee(employee);
        this.setSolicitation(solicitation);
    }
}
