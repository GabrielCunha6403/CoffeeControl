package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.ContributionDto;
import br.com.unifor.coffeecontrol.forms.ContributionProductsForm;
import br.com.unifor.coffeecontrol.forms.ContributionWithProductsForm;
import br.com.unifor.coffeecontrol.forms.UpdatedInventoryForm;
import br.com.unifor.coffeecontrol.forms.UpdatedProductForm;
import br.com.unifor.coffeecontrol.modelos.*;
import br.com.unifor.coffeecontrol.modelos.IdClasses.ContributionsProductsId;
import br.com.unifor.coffeecontrol.repositories.*;
import br.com.unifor.coffeecontrol.services.ContributionService;
import br.com.unifor.coffeecontrol.services.InventoryService;
import br.com.unifor.coffeecontrol.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    private InventoryService inventoryService;

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

        List<ContributionProductsForm> productsInContribution = withProductsForm.getProducts();

        for (int i = 0; i < productsInContribution.size(); i++){
            ContributionProductsForm element = productsInContribution.get(i);
            Optional<Product> productOp = productRepository.findById(element.getId_product());
            Product product = productOp.get();
            ContributionsProductsId id = new ContributionsProductsId(contribution.getId(), product.getId());
            ContributionsProducts contributionsProducts = new ContributionsProducts(id, withProductsForm.getProducts().get(i).getQuantity_received());
            contributionsProducts.setContribution(contribution);
            contributionsProducts.setProduct(product);

            contributionsProductsRepository.save(contributionsProducts);


            int count = contributionsProductsRepository.getReceivedAmountOfOneProductById(product.getId());
            UpdatedInventoryForm form = new UpdatedInventoryForm(count, product.getInventory().getQnt_min());
            inventoryService.updateSpecificInventoryById(product.getInventory().getId(), form);

            if(count >= product.getInventory().getQnt_min()){
                System.out.println("salvado");
            }

        }

        URI uri = uriBuilder.path("/contributions/{id}").buildAndExpand(contribution.getId()).toUri();
        return ResponseEntity.created(uri).body(new ContributionDto(contribution));
    }

    @Override
    public void show(int id){
        Solicitation solicitation = solicitationRepository.getReferenceById(id);
        System.out.println("lll" + solicitation.getProducts());
        solicitation.getListOfProducts(productRepository);
    }


}
