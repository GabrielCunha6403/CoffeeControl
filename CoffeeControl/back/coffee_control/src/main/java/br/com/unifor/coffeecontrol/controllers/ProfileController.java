package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.controllers.dtos.ProductDto;
import br.com.unifor.coffeecontrol.controllers.dtos.ProfileDto;
import br.com.unifor.coffeecontrol.controllers.forms.ProductForm;
import br.com.unifor.coffeecontrol.controllers.forms.ProfileForm;
import br.com.unifor.coffeecontrol.controllers.forms.UpdatedProductForm;
import br.com.unifor.coffeecontrol.controllers.forms.UpdatedProfileForm;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.modelos.Profile;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.repositories.ProfileRepository;
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
    private ProfileRepository profileRepository;

    @GetMapping
    public Page<ProfileDto> listProducts(Pageable paginacao) {
        Page<Profile> profiles = profileRepository.findAll(paginacao);
        return ProfileDto.convert(profiles);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> signUpProduct(@RequestBody ProfileForm profileForm, UriComponentsBuilder uriBuilder){
        Profile profile = profileForm.convert(profileRepository);
        profileRepository.save(profile);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(profile.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProfileDto(profile));
    }

    @GetMapping("/{id}")
    public ProfileDto showSpecificProfileById(@PathVariable int id){
        Profile profile = profileRepository.getReferenceById(id);
        return new ProfileDto(profile);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProfileDto> updateSpecificProfileById(@PathVariable int id, @RequestBody UpdatedProfileForm form){
        Profile profile = form.update(id, profileRepository);
        return ResponseEntity.ok(new ProfileDto(profile));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> deleteSpecificProfileById(@PathVariable int id){
        profileRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
