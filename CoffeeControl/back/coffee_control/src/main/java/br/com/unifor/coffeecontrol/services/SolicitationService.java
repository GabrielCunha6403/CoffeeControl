package br.com.unifor.coffeecontrol.services;

import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface SolicitationService {

    Page<SolicitationDto> listSolicitations(Pageable paginacao);

    ResponseEntity<SolicitationDto> signUpSolicitation(SolicitationForm solicitationForm, UriComponentsBuilder uriBuilder);

    SolicitationDto showSpecificSolicitationById(int id);

    ResponseEntity<SolicitationDto> updateSolicitationById(int id, UpdatedSolicitationForm form);

    ResponseEntity<Solicitation> deleteSpecificSolicitationById(int id);

//    ResponseEntity<SolicitationDto> insertProduct();
}
