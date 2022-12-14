package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

public class SolicitationWithProductsForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int id_employee;
    @Getter @Setter
    private List<SolicitationProductsForm> products;

}
