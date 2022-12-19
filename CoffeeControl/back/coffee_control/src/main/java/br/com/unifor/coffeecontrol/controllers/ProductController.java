package br.com.unifor.coffeecontrol.controllers;

import br.com.unifor.coffeecontrol.dtos.ProductDto;
import br.com.unifor.coffeecontrol.forms.ProductForm;
import br.com.unifor.coffeecontrol.forms.UpdatedProductForm;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    //GET, POST, DELETE

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> listProducts(Pageable paginacao) {
        return productService.listProducts(paginacao);
    }

    @PostMapping
    public ResponseEntity<ProductDto> signUpProduct(@RequestBody ProductForm productForm, UriComponentsBuilder uriBuilder){
        return productService.signUpProduct(productForm, uriBuilder);
    }

    @GetMapping("/{id}")
    public ProductDto showSpecificProductById(@PathVariable int id){
        return productService.showSpecificProductById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> updateSpecificProductById(@PathVariable int id, @RequestBody UpdatedProductForm form){
        return productService.updateSpecificProductById(id, form);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteSpecificProductById(@PathVariable int id){
        return productService.deleteSpecificProductById(id);
    }
    @PostMapping("/filter")
    public List<Object> genericFilterProduct(@RequestBody ProductForm productForm) {
        return productService.genericFilterProduct(productForm);
    }
}
