package br.com.unifor.coffeecontrol.forms;

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
    private int id_employee;

    public Solicitation convert(EmployeeRepository employeeRepository){
        Employee employee = employeeRepository.getReferenceById(this.id_employee);
        return new Solicitation(this.name, employee);
    }
}
