package br.com.unifor.coffeecontrol.forms;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ContributionWithProductsForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int id_employee;
    @Getter @Setter
    private int id_solicitation;
    @Getter @Setter
    private List<ContributionProductsForm> products;

}
