package com.desafio.hotmart.entity;

import com.desafio.hotmart.reuse.util.CollectionHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Stream;

@Entity

@NoArgsConstructor
public class Product extends BaseEntity {

    @Getter
    @Setter
    @Length(max = 100)
    @NotNull
    private String name;

    @Getter
    @Setter
    @Length(max = 500)
    @NotNull
    private String description;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProductCategory category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductAvaliation> avaliations;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Sale> sales;



    public void addAvaliation(ProductAvaliation avaliation) {
        getAvaliations().add(avaliation);
    }

    public List<ProductAvaliation> getAvaliations() {
        avaliations = CollectionHelper.instantiateListIfNecessary(avaliations);
        return avaliations;
    }


    public void addSale(Sale sale) {
        sale.setProduct(this);
        getSales().add(sale);
    }

    public List<Sale> getSales() {
        sales = CollectionHelper.instantiateListIfNecessary(sales);
        return sales;
    }

    @Transient
    public BigDecimal getScore (){
        return getAverageRatingsInLast12Months()
                .add(getNumOfSalesByDay())
                .add(getNewsByProductCategory());
    }

    private BigDecimal getNewsByProductCategory() {
        //TODO: Implments me when consume the news api
        return BigDecimal.ZERO;
    }


    /**
     * Calculate the quantity of sales/days since creating a product.
     * @return total of sales / days since creating a product
     */
    public BigDecimal getNumOfSalesByDay() {
       return BigDecimal.valueOf(getSales().size())
               .divide(BigDecimal.valueOf(getNumDaysExistingProduct()));
    }

    private Integer getNumDaysExistingProduct () {
        Period period = Period.between(getCreateAtInLocalDate(), LocalDate.now());
        return period.getDays();
    }


    /**
     * Calculate the product average ratings  in the last twelve moths
     * @return  product average ratings
     */
    public BigDecimal getAverageRatingsInLast12Months() {
        long totalOfAvaliations = getLastProductAvaliationStream().count();
        int sumOfAvaliations = getLastProductAvaliationStream()
                .mapToInt((ProductAvaliation::getAvaliation))
                .sum();
        return BigDecimal.valueOf(sumOfAvaliations).divide(BigDecimal.valueOf(totalOfAvaliations));
    }

    @Transient
    private Stream<ProductAvaliation> getLastProductAvaliationStream() {
        LocalDate lastMonths = LocalDate.now().minusMonths(12);
        return getAvaliations().stream()
                .filter((avaliation -> avaliation.getCreateAtInLocalDate()
                        .isAfter(lastMonths)));
    }
}
