package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.EmployeeDto;
import br.com.unifor.coffeecontrol.forms.EmployeeForm;
import br.com.unifor.coffeecontrol.forms.UpdatedEmployeeForm;
import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Page<EmployeeDto> listEmployees(Pageable paginacao) {
        Page<Employee> employees = employeeRepository.findAll(paginacao);
        return EmployeeDto.convert(employees);
    }

    @Override
    public ResponseEntity<EmployeeDto> signUpEmployee(EmployeeForm employeeForm, UriComponentsBuilder uriBuilder) {
        Employee employee = employeeForm.convert(employeeRepository);
        employeeRepository.save(employee);

        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmployeeDto(employee));
    }

    @Override
    public EmployeeDto showSpecificEmployeeById(int id) {
        Employee employee = employeeRepository.getReferenceById(id);
        System.out.println(employee.getProfile());
        return new EmployeeDto(employee);
    }

    @Override
    public ResponseEntity<EmployeeDto> updateSpecificEmployeeById(int id, UpdatedEmployeeForm form) {
        Employee employee = form.update(id, employeeRepository);
        return ResponseEntity.ok(new EmployeeDto(employee));
    }

    @Override
    public ResponseEntity<Employee> deleteSpecificEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
