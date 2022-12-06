package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.controllers.dtos.EmployeeDto;
import br.com.unifor.coffeecontrol.controllers.dtos.ProductDto;
import br.com.unifor.coffeecontrol.controllers.forms.EmployeeForm;
import br.com.unifor.coffeecontrol.controllers.forms.ProductForm;
import br.com.unifor.coffeecontrol.controllers.forms.UpdatedEmployeeForm;
import br.com.unifor.coffeecontrol.controllers.forms.UpdatedProductForm;
import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public Page<EmployeeDto> listarTopicos(Pageable paginacao) {
        Page<Employee> employees = employeeRepository.findAll(paginacao);
        return EmployeeDto.convert(employees);
    }
    @PostMapping
    public ResponseEntity<EmployeeDto> signUpProduct(@RequestBody EmployeeForm employeeForm, UriComponentsBuilder uriBuilder){
        Employee employee = employeeForm.convert(employeeRepository);
        employeeRepository.save(employee);

        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmployeeDto(employee));
    }

    @GetMapping("/{id}")
    public EmployeeDto showSpecificEmployeeById(@PathVariable int id){
        Employee employee = employeeRepository.getReferenceById(id);
        return new EmployeeDto(employee);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmployeeDto> updateSpecificEmployeeById(@PathVariable int id, @RequestBody UpdatedEmployeeForm form){
        Employee employee = form.update(id, employeeRepository);
        return ResponseEntity.ok(new EmployeeDto(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteSpecificEmployeeById(@PathVariable int id){
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
