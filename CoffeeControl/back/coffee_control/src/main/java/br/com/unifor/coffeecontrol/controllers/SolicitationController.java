package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.forms.SolicitationWithProductsForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.modelos.SolicitationsProducts;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import br.com.unifor.coffeecontrol.services.SolicitationService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    public ResponseEntity<SolicitationDto> signUpSolicitation(@RequestBody SolicitationWithProductsForm solicitationForm, UriComponentsBuilder uriBuilder){
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

    @GetMapping("/{id_solicitation}/has/{id_product}")
    public Boolean findProductInSolicitation(@PathVariable Integer id_solicitation, @PathVariable Integer id_product){
        return solicitationService.findProductInSolicitation(id_solicitation, id_product);
    }

    @GetMapping("/count/{id}")
    public Integer count(@PathVariable Integer id){
        return solicitationService.count(id);
    }
}
