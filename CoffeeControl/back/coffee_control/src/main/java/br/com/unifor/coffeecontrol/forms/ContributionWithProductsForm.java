package br.com.unifor.coffeecontrol.forms;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ContributionWithProductsForm {
    private String name;
    private int id_employee;
    private int id_solicitation;
    private List<ContributionProductsForm> products;

}
