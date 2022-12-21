package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Employee;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailDto {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String type_of_profile;
    @Getter
    private int registration;
    @Getter
    private String password;
    @Getter
    private Boolean enable;
    @Getter
    private List<ContributionSubDetailDto> contributions = new ArrayList<ContributionSubDetailDto>();
    @Getter
    private List<SolicitationSubDetailDto> solicitations = new ArrayList<SolicitationSubDetailDto>();

    public EmployeeDetailDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.type_of_profile = employee.getProfile().getName();
        this.registration = employee.getRegistration();
        this.password = employee.getPassword();
        this.enable = employee.getEnable();
        employee.getContributions().forEach(contribution -> {
            this.contributions.add(new ContributionSubDetailDto(contribution));
        });
        employee.getSolicitations().forEach((solicitation -> {
            this.solicitations.add(SolicitationSubDetailDto.convert(solicitation));
        }));
    }
    public static Page<EmployeeDetailDto> convert(Page<Employee> employees) {
        return employees.map(EmployeeDetailDto::new);
    }

}
