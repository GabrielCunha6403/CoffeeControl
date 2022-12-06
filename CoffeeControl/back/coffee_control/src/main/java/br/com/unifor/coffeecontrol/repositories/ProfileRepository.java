package br.com.unifor.coffeecontrol.repositories;

import br.com.unifor.coffeecontrol.modelos.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
