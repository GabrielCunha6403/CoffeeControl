package br.com.unifor.coffeecontrol.services;

import br.com.unifor.coffeecontrol.dtos.ContributionDto;
import br.com.unifor.coffeecontrol.forms.ContributionForm;
import br.com.unifor.coffeecontrol.forms.ContributionWithProductsForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface ContributionService {
    Page<ContributionDto> listContributions(Pageable paginacao);

    ResponseEntity<ContributionDto> signUpContribution(ContributionWithProductsForm withProductsForm, UriComponentsBuilder uriBuilder);

    void show(int id);

}
