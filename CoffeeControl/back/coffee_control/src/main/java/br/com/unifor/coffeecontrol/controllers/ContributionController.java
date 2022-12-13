package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.dtos.ContributionDto;
import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.ContributionForm;
import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.services.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/contributions")
@RestController
public class ContributionController {

    @Autowired
    private ContributionService contributionService;

    @GetMapping
    public Page<ContributionDto> listContributions(Pageable paginacao) {
        return contributionService.listContributions(paginacao);
    }

    @PostMapping
    public ResponseEntity<ContributionDto> signUpContribution(@RequestBody ContributionForm contributionForm, UriComponentsBuilder uriBuilder){
        return contributionService.signUpContribution(contributionForm, uriBuilder);
    }
}
