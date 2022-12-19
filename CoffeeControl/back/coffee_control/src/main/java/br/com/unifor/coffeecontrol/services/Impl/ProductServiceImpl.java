package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.dtos.ProductDto;
import br.com.unifor.coffeecontrol.forms.ProductForm;
import br.com.unifor.coffeecontrol.forms.UpdatedProductForm;
import br.com.unifor.coffeecontrol.modelos.Product;
import br.com.unifor.coffeecontrol.repositories.InventoryRepository;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public Page<ProductDto> listProducts(Pageable paginacao) {
        Page<Product> products = productRepository.findAll(paginacao);
        return ProductDto.convert(products);
    }

    @Override
    public ResponseEntity<ProductDto> signUpProduct(ProductForm productForm, UriComponentsBuilder uriBuilder) {
        Product product = productForm.convert();
        product.getInventory();
        inventoryRepository.save(product.getInventory());
        productRepository.save(product);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

    @Override
    public ProductDto showSpecificProductById(int id) {
        Product product = productRepository.getReferenceById(id);
        return new ProductDto(product);
    }

    @Override
    public ResponseEntity<ProductDto> updateSpecificProductById(int id, UpdatedProductForm form) {
        Product product = form.update(id, productRepository);
        return ResponseEntity.ok(new ProductDto(product));
    }

    @Override
    public ResponseEntity<Product> deleteSpecificProductById(int id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Object> genericFilterProduct(ProductForm productForm) {
        return productRepository.genericFilterProduct(productForm.getName(), productForm.getDescription(), productForm.getEnable());
    }

}
