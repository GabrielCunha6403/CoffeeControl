package br.com.unifor.coffeecontrol.controllers.forms;

import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.modelos.Profile;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.repositories.ProfileRepository;
import lombok.Getter;
import lombok.Setter;

public class ProfileForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;

    public Profile convert(ProfileRepository profileRepository){
        return new Profile(this.name, this.description);
    }
}
