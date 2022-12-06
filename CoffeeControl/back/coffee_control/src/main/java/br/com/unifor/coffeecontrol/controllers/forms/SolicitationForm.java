package br.com.unifor.coffeecontrol.controllers.forms;

import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class SolicitationForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String name_employee;

    public Solicitation convert(EmployeeRepository employeeRepository){
        Employee employee = employeeRepository.findByName(this.name_employee);
        return new Solicitation(this.name, employee);
    }
}
