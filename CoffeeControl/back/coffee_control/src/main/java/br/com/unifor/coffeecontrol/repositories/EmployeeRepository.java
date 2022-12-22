package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByName(String name);

    @Query(value = "select * from employees e\n" +
            "\twhere (lower(e.name) like lower(concat('%', :name, '%')) or :name is null) \n" +
            "\tand (lower(e.registration) like :registration or cast(:registration as int) = 0) \n" +
            "\tand (e.id_profile = :profile or :profile is null) \n" +
            "\tand (e.enable = :enable or :enable is null)", nativeQuery = true)
    List<Object> genericFilter(String name, String registration, Boolean enable, Integer profile);
}
