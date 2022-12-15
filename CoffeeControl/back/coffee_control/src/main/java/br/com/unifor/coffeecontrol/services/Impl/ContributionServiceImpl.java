package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.ContributionDto;
import br.com.unifor.coffeecontrol.forms.ContributionProductsForm;
import br.com.unifor.coffeecontrol.forms.ContributionWithProductsForm;
import br.com.unifor.coffeecontrol.modelos.*;
import br.com.unifor.coffeecontrol.modelos.IdClasses.ContributionsProductsId;
import br.com.unifor.coffeecontrol.repositories.*;
import br.com.unifor.coffeecontrol.services.ContributionService;
import br.com.unifor.coffeecontrol.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContributionServiceImpl implements ContributionService {

    @Autowired
    private ContributionRepository contributionRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private ContributionsProductsRepository contributionsProductsRepository;
    @Autowired
    private SolicitationService solicitationService;

    @Override
    public Page<ContributionDto> listContributions(Pageable paginacao) {
        Page<Contribution> contributions = contributionRepository.findAll(paginacao);
        return ContributionDto.convert(contributions);
    }

    @Override
    public ResponseEntity<ContributionDto> signUpContribution(ContributionWithProductsForm withProductsForm, UriComponentsBuilder uriBuilder) {
        Employee employee = employeeRepository.getReferenceById(withProductsForm.getId_employee());
        Solicitation solicitation = solicitationRepository.getReferenceById(withProductsForm.getId_solicitation());

        Contribution contribution = new Contribution(LocalDate.now(), employee, solicitation);
        contributionRepository.save(contribution);

        List<ContributionProductsForm> products = withProductsForm.getProducts();
        List<Product> productList = new ArrayList<Product>();
//        products.forEach(element -> {
//            Product product = productRepository.getReferenceById(element.getId_product());
//            productList.add(product);
//        });

        for (int i = 0; i < products.size(); i++){
            ContributionProductsForm element = products.get(i);
            Product product = productRepository.getReferenceById(element.getId_product());
            ContributionsProductsId id = new ContributionsProductsId(contribution.getId(), product.getId());
            ContributionsProducts contributionsProducts = new ContributionsProducts(id, withProductsForm.getProducts().get(i).getQuantity_received());
            contributionsProducts.setContribution(contribution);
            contributionsProducts.setProduct(product);

            contributionsProductsRepository.save(contributionsProducts);
        }

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
