package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByName(String name);
}
