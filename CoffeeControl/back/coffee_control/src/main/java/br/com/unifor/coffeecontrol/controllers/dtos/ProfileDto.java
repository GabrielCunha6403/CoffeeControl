package br.com.unifor.coffeecontrol.controllers.dtos;

import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.Profile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class ProfileDto {
    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String description;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.description = profile.getDescription();
    }

    public static Page<ProfileDto> convert(Page<Profile> profiles) {
        return profiles.map(ProfileDto::new);
    }
}
