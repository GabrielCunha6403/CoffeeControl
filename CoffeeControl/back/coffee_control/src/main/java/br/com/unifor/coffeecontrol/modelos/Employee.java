package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int registration;
    @Getter @Setter
    private String password;
    @Getter @Setter
    @OneToMany(mappedBy = "employee")
    private List<Contribution> contributions;
    @Getter @Setter
    @OneToMany(mappedBy = "employee")
    private List<Solicitation> solicitations;
    @Getter @Setter
    @ManyToOne
    private Profile profile;



    public Employee(String name, int registration, String password) {
        this.name = name;
        this.registration = registration;
        this.password = password;
    }
}
