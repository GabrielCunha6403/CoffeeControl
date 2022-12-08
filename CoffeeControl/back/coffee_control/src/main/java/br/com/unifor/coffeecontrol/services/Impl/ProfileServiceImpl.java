package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.ProfileDto;
import br.com.unifor.coffeecontrol.forms.ProfileForm;
import br.com.unifor.coffeecontrol.forms.UpdatedProfileForm;
import br.com.unifor.coffeecontrol.modelos.Profile;
import br.com.unifor.coffeecontrol.repositories.ProfileRepository;
import br.com.unifor.coffeecontrol.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Page<ProfileDto> listProfiles(Pageable paginacao) {
        Page<Profile> profiles = profileRepository.findAll(paginacao);
        return ProfileDto.convert(profiles);
    }

    @Override
    public ResponseEntity<ProfileDto> signUpProfile(ProfileForm profileForm, UriComponentsBuilder uriBuilder) {
        Profile profile = profileForm.convert(profileRepository);
        profileRepository.save(profile);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(profile.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfileDto(profile));
    }

    @Override
    public ProfileDto showSpecificProfileById(int id) {
        Profile profile = profileRepository.getReferenceById(id);
        return new ProfileDto(profile);
    }

    @Override
    public ResponseEntity<ProfileDto> updateSpecificProfileById(int id, UpdatedProfileForm form) {
        Profile profile = form.update(id, profileRepository);
        return ResponseEntity.ok(new ProfileDto(profile));
    }

    @Override
    public ResponseEntity<Profile> deleteSpecificProfileById(int id) {
        profileRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
