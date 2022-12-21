package br.com.unifor.coffeecontrol.services;

import br.com.unifor.coffeecontrol.dtos.SolicitationDetailDto;
import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationWithProductsForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface SolicitationService {

    Page<SolicitationDto> listSolicitations(Pageable paginacao);

    Page<SolicitationDetailDto> listSolicitationDetail(Pageable paginacao);

    ResponseEntity<SolicitationDto> signUpSolicitation(SolicitationWithProductsForm withProductsForm, UriComponentsBuilder uriBuilder);

    SolicitationDto showSpecificSolicitationById(int id);

    ResponseEntity<SolicitationDto> updateSolicitationById(int id, UpdatedSolicitationForm form);

    ResponseEntity<Solicitation> deleteSpecificSolicitationById(int id);

    Boolean findProductInSolicitation(Integer id_solicitation, Integer id_product);

    Integer count(Integer id);
}
