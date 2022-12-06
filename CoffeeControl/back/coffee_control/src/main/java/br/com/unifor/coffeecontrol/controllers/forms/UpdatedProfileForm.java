package br.com.unifor.coffeecontrol.controllers.forms;

import br.com.unifor.coffeecontrol.modelos.Profile;
import br.com.unifor.coffeecontrol.repositories.ProfileRepository;
import lombok.Getter;
import lombok.Setter;

public class UpdatedProfileForm {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;

    public Profile update(int id, ProfileRepository profileRepository) {
        Profile profile = profileRepository.getReferenceById(id);
        profile.setName(this.name);
        profile.setDescription(this.description);
        return profile;
    }
}
