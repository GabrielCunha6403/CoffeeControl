package br.com.unifor.coffeecontrol.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "inventories")
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;
    @Getter @Setter
    private int qnt_now;
    @Getter @Setter
    private int qnt_min;

    public Inventory(Integer qnt_now, Integer qnt_min) {
        this.qnt_now = qnt_now;
        this.qnt_min = qnt_min;
    }
}
