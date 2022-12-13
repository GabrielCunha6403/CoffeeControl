package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.ContributionDto;
import br.com.unifor.coffeecontrol.dtos.ProductDto;
import br.com.unifor.coffeecontrol.forms.ContributionForm;
import br.com.unifor.coffeecontrol.forms.ProductForm;
import br.com.unifor.coffeecontrol.modelos.Contribution;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.ContributionRepository;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.services.ContributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<ContributionDto> listContributions(Pageable paginacao) {
        Page<Contribution> contributions = contributionRepository.findAll(paginacao);
        return ContributionDto.convert(contributions);
    }

    @Override
    public ResponseEntity<ContributionDto> signUpContribution(ContributionForm contributionForm, UriComponentsBuilder uriBuilder) {
        Contribution contribution = contributionForm.convert(employeeRepository);
        contributionRepository.save(contribution);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(contribution.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContributionDto(contribution));
    }

//    @Override
//    public ResponseEntity<ContributionDto> addProductById(int id, ProductForm productForm){
//        Contribution contribution = contributionRepository.getReferenceById(id);
//        Product product = productForm.convert(productRepository);
//        contribution.getProducts().add(product);
//    }
}
