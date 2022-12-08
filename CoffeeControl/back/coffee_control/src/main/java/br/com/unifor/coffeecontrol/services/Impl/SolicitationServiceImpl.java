package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import br.com.unifor.coffeecontrol.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class SolicitationServiceImpl implements SolicitationService {

    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<SolicitationDto> listSolicitations(Pageable paginacao) {
        Page<Solicitation> solicitation = solicitationRepository.findAll(paginacao);
        return SolicitationDto.convert(solicitation);
    }

    @Override
    public ResponseEntity<SolicitationDto> signUpSolicitation(SolicitationForm solicitationForm, UriComponentsBuilder uriBuilder) {
        Solicitation solicitation = solicitationForm.convert(employeeRepository);
        solicitationRepository.save(solicitation);

        URI uri = uriBuilder.path("/solicitations/{id}").buildAndExpand(solicitation.getId()).toUri();
        return ResponseEntity.created(uri).body(new SolicitationDto(solicitation));
    }

    @Override
    public SolicitationDto showSpecificSolicitationById(int id) {
        Solicitation solicitation = solicitationRepository.getReferenceById(id);
        return new SolicitationDto(solicitation);
    }

    @Override
    public ResponseEntity<SolicitationDto> updateSolicitationById(int id, UpdatedSolicitationForm form) {
        Solicitation solicitation = form.update(id, solicitationRepository);
        return ResponseEntity.ok(new SolicitationDto(solicitation));
    }

    @Override
    public ResponseEntity<Solicitation> deleteSpecificSolicitationById(int id) {
        solicitationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
