package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "profiles")
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @Getter @Setter
    @OneToMany(mappedBy = "profile")
    private List<Employee> employees;

    public Profile(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
