package br.com.unifor.coffeecontrol.services;

import br.com.unifor.coffeecontrol.dtos.ProfileDto;
import br.com.unifor.coffeecontrol.forms.ProfileForm;
import br.com.unifor.coffeecontrol.forms.UpdatedProfileForm;
import br.com.unifor.coffeecontrol.modelos.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface ProfileService {

    Page<ProfileDto> listProfiles(Pageable paginacao);

    ResponseEntity<ProfileDto> signUpProfile(ProfileForm profileForm, UriComponentsBuilder uriBuilder);

    ProfileDto showSpecificProfileById(int id);

    ResponseEntity<ProfileDto> updateSpecificProfileById(int id, UpdatedProfileForm form);

    ResponseEntity<Profile> deleteSpecificProfileById(int id);
}
