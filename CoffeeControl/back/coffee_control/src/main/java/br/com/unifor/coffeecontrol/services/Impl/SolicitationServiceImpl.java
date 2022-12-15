package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.SolicitationDto;
import br.com.unifor.coffeecontrol.forms.SolicitationProductsForm;
import br.com.unifor.coffeecontrol.forms.SolicitationWithProductsForm;
import br.com.unifor.coffeecontrol.forms.UpdatedSolicitationForm;
import br.com.unifor.coffeecontrol.modelos.Employee;
import br.com.unifor.coffeecontrol.modelos.IdClasses.SolicitationsProductsId;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.modelos.Solicitation;
import br.com.unifor.coffeecontrol.modelos.SolicitationsProducts;
import br.com.unifor.coffeecontrol.repositories.EmployeeRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationRepository;
import br.com.unifor.coffeecontrol.repositories.SolicitationsProductsRepository;
import br.com.unifor.coffeecontrol.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class SolicitationServiceImpl implements SolicitationService {

    @Autowired
    private SolicitationRepository solicitationRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SolicitationsProductsRepository solicitationsProductsRepository;

    @Override
    public Page<SolicitationDto> listSolicitations(Pageable paginacao) {
        Page<Solicitation> solicitation = solicitationRepository.findAll(paginacao);
        return SolicitationDto.convert(solicitation);
    }

    @Override
    public ResponseEntity<SolicitationDto> signUpSolicitation(SolicitationWithProductsForm withProductsForm, UriComponentsBuilder uriBuilder) {
        Employee employee = employeeRepository.getReferenceById(withProductsForm.getId_employee());
        Solicitation solicitation = new Solicitation(withProductsForm.getName(), employee);
        solicitationRepository.save(solicitation);

        List<SolicitationProductsForm> productsForm = withProductsForm.getProducts();
        for (int i = 0; i < withProductsForm.getProducts().size(); i++){
            SolicitationProductsForm element = productsForm.get(i);
            Product product = productRepository.getReferenceById(element.getId_product());
            SolicitationsProductsId solicitationsProductsId = new SolicitationsProductsId(solicitation.getId(), product.getId());
            SolicitationsProducts solicitationsProducts = new SolicitationsProducts(solicitationsProductsId, product.getQnt_min_inventory());
            solicitationsProducts.setProduct(product);
            solicitationsProducts.setSolicitation(solicitation);
            solicitationsProductsRepository.save(solicitationsProducts);
        };

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
    @Override
    public Boolean findProductInSolicitation(Integer id_solicitation, Integer id_product){
        return solicitationsProductsRepository.findProductInSolicitation(id_solicitation, id_product);
    }

//    @Override
//    public ResponseEntity<SolicitationDto> insertProduct(int id, int id_product) {
//        Solicitation solicitation = solicitationRepository.getReferenceById(id);
//        Product product = productRepository.getReferenceById(id_product);
//
//        SolicitationsProductsId solicitationsProductsId = new SolicitationsProductsId(solicitation.getId(), product.getId());
//        SolicitationsProducts solicitationsProducts = new SolicitationsProducts(solicitationsProductsId, );
//
//        return null;
//    }
}
//    @Override
//    public ResponseEntity<SolicitationDto> register(SolicitationPostForm form, UriComponentsBuilder uriBuilder) throws Exception {
//        User user= Optional.of(userRepository.findByName(form.getUsername()).get(0)).orElseThrow(() -> new Exception("user not found"));
//        Solicitation solicitation = new Solicitation(form,user);
//        solicitation= solicitationRepository.save(solicitation);
//        for(int i =0; i < form.getProducts().length;i++) {
//            Integer productId=form.getProducts()[i].getProductId();
//            Integer requiredAmount=form.getProducts()[i].getRequiredAmount();
//            Product product =productRepository.findById(productId).orElseThrow(() -> new Exception("product id invalid"));
//            SolicitationProductId solicitationProductId = new SolicitationProductId(productId,solicitation.getId());
//            SolicitationProduct solicitationProduct=new SolicitationProduct(solicitationProductId,requiredAmount);
//            solicitationProduct.setSolicitation(solicitation);
//            solicitationProduct.setProduct(product);
//            solicitationProductRepository.save(solicitationProduct);
//        }
//        URI uri= uriBuilder.path("solications/{id}").buildAndExpand(solicitation.getId()).toUri();
//        return ResponseEntity.created(uri).body(new SolicitationDto(solicitation));
//    }
