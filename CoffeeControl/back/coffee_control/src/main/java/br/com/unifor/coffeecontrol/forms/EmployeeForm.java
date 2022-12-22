package br.com.unifor.coffeecontrol.forms;

import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeForm {
    private String name;
    private int registration;
    private String password;
    private Boolean enable;
    private Integer profile;

    public Employee convert(EmployeeRepository employeeRepository){
        Employee employee = employeeRepository.findByName(this.name);
        return new Employee(this.name, this.registration, this.password);
    }

    public EmployeeForm(String name, int registration, Boolean enable, Integer profile) {
        this.name = name;
        this.registration = registration;
        this.enable = enable;
        this.profile = profile;
    }
}
