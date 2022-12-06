package br.com.unifor.coffeecontrol.controllers.forms;

import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import lombok.Getter;
import lombok.Setter;

public class UpdatedEmployeeForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int registration;
    @Getter @Setter
    private String password;

    public Employee update(int id, EmployeeRepository repository){
        Employee employee = repository.getReferenceById(id);
        employee.setName(this.name);
        employee.setRegistration(this.registration);
        employee.setPassword(this.password);
        return employee;
    }
}
