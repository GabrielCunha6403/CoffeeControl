package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import br.com.unifor.coffeecontrol.services.SolicitationService;
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
    private SolicitationService solicitationService;

    @GetMapping
    public Page<SolicitationDto> listSolicitations(Pageable paginacao) {
        return solicitationService.listSolicitations(paginacao);
    }
    @PostMapping
    public ResponseEntity<SolicitationDto> signUpSolicitation(@RequestBody SolicitationForm solicitationForm, UriComponentsBuilder uriBuilder){
        return solicitationService.signUpSolicitation(solicitationForm, uriBuilder);
    }

    @GetMapping("/{id}")
    public SolicitationDto showSpecificSolicitationById(@PathVariable int id){
        return solicitationService.showSpecificSolicitationById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitationDto> updateSolicitationById(@PathVariable int id, @RequestBody UpdatedSolicitationForm form){
        return solicitationService.updateSolicitationById(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Solicitation> deleteSpecificSolicitationById(@PathVariable int id){
        return solicitationService.deleteSpecificSolicitationById(id);
    }

//    @PostMapping("/insert_product/{id}")
//    public ResponseEntity<SolicitationDto> insertProduct(@PathVariable int id, ProductRepository productRepository,@RequestBody int id_product) {
//        System.out.println("passou aqui");
//        return solicitationService.insertProduct(id, productRepository, id_product);
//    }
}
