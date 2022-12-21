package br.com.unifor.coffeecontrol.dtos;

import br.com.unifor.coffeecontrol.modelos.Contribution;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ContributionSubDetailDto {

        @Getter
        private int id;
        @Getter
        private int id_solicitation;
        @Getter
        private LocalDate date;
        @Getter
        private List<ProductDto> products = new ArrayList<ProductDto>();

        public ContributionSubDetailDto(Contribution contribution){
                this.id = contribution.getId();
                this.id_solicitation = contribution.getSolicitation().getId();
                this.date = contribution.getDate();
                contribution.getProducts().forEach(product -> {
                        this.products.add(new ProductDto(product.getProduct()));
                });
        }

        public static ContributionSubDetailDto convert(Contribution contribution) {
                return new ContributionSubDetailDto(contribution);
        }
}

