package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Contribution;
import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.ContributionRepository;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ContributionForm {
    @Getter @Setter
    private LocalDate date;
    @Getter @Setter
    private String name_employee;

//    public Contribution convert(EmployeeRepository employeeRepository){
//        Employee employee = employeeRepository.findByName(this.name_employee);
//        return new Contribution(this.date, employee);
//    }
}
