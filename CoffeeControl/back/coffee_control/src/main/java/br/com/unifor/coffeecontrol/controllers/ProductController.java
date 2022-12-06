package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.controllers.dtos.ProductDto;
import br.com.unifor.coffeecontrol.controllers.forms.ProductForm;
import br.com.unifor.coffeecontrol.controllers.forms.UpdatedProductForm;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    //GET, POST, DELETE

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public Page<ProductDto> listProducts(Pageable paginacao) {
        Page<Product> products = productRepository.findAll(paginacao);
        return ProductDto.convert(products);
    }

    @PostMapping
    public ResponseEntity<ProductDto> signUpProduct(@RequestBody ProductForm productForm, UriComponentsBuilder uriBuilder){
        Product product = productForm.convert(productRepository);
        productRepository.save(product);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

    @GetMapping("/{id}")
    public ProductDto showSpecificProductById(@PathVariable int id){
        Product product = productRepository.getReferenceById(id);
        return new ProductDto(product);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> updateSpecificProductById(@PathVariable int id, @RequestBody UpdatedProductForm form){
        Product product = form.update(id, productRepository);
        return ResponseEntity.ok(new ProductDto(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteSpecificProductById(@PathVariable int id){
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
