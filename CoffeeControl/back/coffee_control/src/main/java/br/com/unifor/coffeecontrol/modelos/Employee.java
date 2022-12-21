package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
@NoArgsConstructor
@Getter @Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer registration;
    private String password;
    private Boolean enable;
    @OneToMany(mappedBy = "employee")
    private List<Contribution> contributions;
    @OneToMany(mappedBy = "employee")
    private List<Solicitation> solicitations;
    @ManyToOne
    @JoinColumn(name = "id_profile")
    private Profile profile;

    public Employee(String name, int registration, String password) {
        this.name = name;
        this.registration = registration;
        this.password = password;
    }

    public Employee(String name, Integer registration, String password, Boolean enable) {
        this.name = name;
        this.registration = registration;
        this.password = password;
        this.enable = enable;
    }
}
