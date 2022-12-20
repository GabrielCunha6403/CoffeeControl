package br.com.unifor.coffeecontrol.modelos;

import br.com.unifor.coffeecontrol.forms.ProductForm;
import br.com.unifor.coffeecontrol.forms.SolicitationProductsForm;
import br.com.unifor.coffeecontrol.repositories.ProductRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "solicitations")
@NoArgsConstructor
@Getter @Setter
public class Solicitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
    private String name;
    private LocalDate date;
    private Boolean enable;
    @OneToMany(mappedBy = "solicitation")
    private List<Contribution> contributions;
    @OneToMany(mappedBy = "solicitation")
    private List<SolicitationsProducts> products;

    public Solicitation(String name, Employee employee) {
        this.name = name;
        this.date = LocalDate.now();
        this.employee = employee;
    }

    public Solicitation(String name, Employee employee, List<ProductForm> products) {
        this.name = name;
        this.employee = employee;

    }

    public List<Product> getListOfProducts(ProductRepository repository){
        List<Product> productList = new ArrayList<Product>();
        this.getProducts().forEach( product -> {
            productList.add(product.getProduct());
        });

        return productList;
    }

}
