package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByName(String name);

    @Query(value = "select * from employees e \n" +
            "\twhere (lower(e.name) like concat('%', :name, '%') or :name is null) \n" +
            "\tand (lower(e.registration) like :registration or :registration is null)\n" +
            "\tand (e.id_profile = :enable or :enable is null)\n" +
            "\tand (e.enable = :profile or :profile is null)", nativeQuery = true)
    List<Object> genericFilter(String name, Integer registration, Boolean enable, Integer profile);
}
