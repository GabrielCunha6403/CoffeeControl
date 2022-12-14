package br.com.unifor.coffeecontrol.forms;

import lombok.Getter;
import lombok.Setter;

public class ContributionProductsForm {
    @Getter @Setter
    private int id_product;
    @Getter @Setter
    private int quantity_received;
}
