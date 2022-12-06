package br.com.unifor.coffeecontrol.services.Impl;

import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import br.com.unifor.coffeecontrol.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceImpl implements ProductService {

    //Tratamento dos dados

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void coisarUmaCoisa() {

    }
}
