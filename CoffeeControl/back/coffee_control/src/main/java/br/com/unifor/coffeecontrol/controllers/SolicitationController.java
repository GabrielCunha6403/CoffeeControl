package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequestMapping("/solicitations")
@RestController
public class SolicitationController {

    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public Page<SolicitationDto> listSolicitations(Pageable paginacao) {
        Page<Solicitation> solicitation = solicitationRepository.findAll(paginacao);
        return SolicitationDto.convert(solicitation);
    }
    @PostMapping
    public ResponseEntity<SolicitationDto> signUpProduct(@RequestBody SolicitationForm solicitationForm, UriComponentsBuilder uriBuilder){
        Solicitation solicitation = solicitationForm.convert(employeeRepository);
        solicitationRepository.save(solicitation);

        URI uri = uriBuilder.path("/solicitations/{id}").buildAndExpand(solicitation.getId()).toUri();
        return ResponseEntity.created(uri).body(new SolicitationDto(solicitation));
    }

    @GetMapping("/{id}")
    public SolicitationDto showSpecificSolicitationById(@PathVariable int id){
        Solicitation solicitation = solicitationRepository.getReferenceById(id);
        return new SolicitationDto(solicitation);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SolicitationDto> updateSpecificSolicitationById(@PathVariable int id, @RequestBody UpdatedSolicitationForm form){
        Solicitation solicitation = form.update(id, solicitationRepository);
        return ResponseEntity.ok(new SolicitationDto(solicitation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Solicitation> deleteSpecificSolicitationById(@PathVariable int id){
        solicitationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
