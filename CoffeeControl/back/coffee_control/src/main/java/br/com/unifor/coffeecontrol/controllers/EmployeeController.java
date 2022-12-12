package br.com.unifor.coffeecontrol.controllers;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public Page<EmployeeDto> listEmployees(Pageable paginacao) {
        return employeeService.listEmployees(paginacao);
    }
    @PostMapping
    public ResponseEntity<EmployeeDto> signUpEmployee(@RequestBody EmployeeForm employeeForm, UriComponentsBuilder uriBuilder){
        return employeeService.signUpEmployee(employeeForm, uriBuilder);
    }

    @GetMapping("/{id}")
    public EmployeeDto showSpecificEmployeeById(@PathVariable int id){
        return employeeService.showSpecificEmployeeById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmployeeDto> updateSpecificEmployeeById(@PathVariable int id, @RequestBody UpdatedEmployeeForm form){
        return employeeService.updateSpecificEmployeeById(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteSpecificEmployeeById(@PathVariable int id){
        return employeeService.deleteSpecificEmployeeById(id);
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<EmployeeDto> toggleEnableById(@PathVariable int id, EmployeeRepository repository){
//        return employeeService.toggleEnableById(id, repository);
//    }
}
