package br.com.unifor.coffeecontrol.services;

import br.com.unifor.coffeecontrol.dtos.EmployeeDetailDto;
import br.com.unifor.coffeecontrol.dtos.EmployeeDto;
import br.com.unifor.coffeecontrol.forms.EmployeeForm;
import br.com.unifor.coffeecontrol.forms.UpdatedEmployeeForm;
import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public interface EmployeeService {
    Page<EmployeeDto> listEmployees(Pageable paginacao);

    Page<EmployeeDetailDto> listEmployeeDetail(Pageable paginacao);

    ResponseEntity<EmployeeDto> signUpEmployee(EmployeeForm employeeForm, UriComponentsBuilder uriBuilder);

    EmployeeDto showSpecificEmployeeById(int id);

    ResponseEntity<EmployeeDto> updateSpecificEmployeeById(int id, UpdatedEmployeeForm form);

    ResponseEntity<Employee> deleteSpecificEmployeeById(int id);

    ResponseEntity<EmployeeDto> toggleEnableById(int id, EmployeeRepository repository);

    List<Object> genericFilter(EmployeeForm form);
}
