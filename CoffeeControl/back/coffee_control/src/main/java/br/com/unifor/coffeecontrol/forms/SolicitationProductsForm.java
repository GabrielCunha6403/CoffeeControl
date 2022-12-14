package br.com.unifor.coffeecontrol.forms;

import lombok.Getter;
import lombok.Setter;

public class SolicitationProductsForm {
    @Getter @Setter
    private int id_product;
    @Getter @Setter
    private int qnt_min_inventory;
}
