package br.com.unifor.coffeecontrol.controllers;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequestMapping("/profiles")
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public Page<ProfileDto> listProfiles(Pageable paginacao) {
        return profileService.listProfiles(paginacao);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> signUpProfile(@RequestBody ProfileForm profileForm, UriComponentsBuilder uriBuilder){
        return profileService.signUpProfile(profileForm, uriBuilder);
    }

    @GetMapping("/{id}")
    public ProfileDto showSpecificProfileById(@PathVariable int id){
        return profileService.showSpecificProfileById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProfileDto> updateSpecificProfileById(@PathVariable int id, @RequestBody UpdatedProfileForm form){
        return profileService.updateSpecificProfileById(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteSpecificProfileById(@PathVariable int id){
        return profileService.deleteSpecificProfileById(id);
    }
}
