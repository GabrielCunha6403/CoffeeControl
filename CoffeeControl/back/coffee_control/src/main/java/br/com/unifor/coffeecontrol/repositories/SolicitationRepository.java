package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends JpaRepository<Solicitation, Integer> {
    Solicitation findById(int id);

    Solicitation findByName(String name);

}
