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
    @OneToOne
//    @Column(name = "id_product")
    private Product product;
    @Getter @Setter
    private int qnt_now;
    @Getter @Setter
    private int qnt_min;
}
