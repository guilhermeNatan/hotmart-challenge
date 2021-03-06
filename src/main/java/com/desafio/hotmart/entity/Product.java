package com.desafio.hotmart.entity;

import com.desafio.hotmart.reuse.util.CollectionHelper;
import com.desafio.hotmart.reuse.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    @JsonIgnore
    private ProductCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductAvaliation> avaliations;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Sale> sales;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @Column(columnDefinition="decimal(10,2) default '0.00'")
    private BigDecimal score = BigDecimal.ZERO;

    public void addAvaliation(ProductAvaliation avaliation) {
        avaliation.setProduct(this);
        getAvaliations().add(avaliation);
    }

    @JsonIgnore
    public List<ProductAvaliation> getAvaliations() {
        avaliations = CollectionHelper.instantiateListIfNecessary(avaliations);
        return avaliations;
    }


    @Transient
    @JsonProperty("category")
    public String getCategoryName() {
       return this.getCategory().getName();
    }



    public void addSale(Sale sale) {
        sale.setProduct(this);
        getSales().add(sale);
    }

    public List<Sale> getSales() {
        sales = CollectionHelper.instantiateListIfNecessary(sales);
        return sales;
    }


    public void updateScore() {
        this.setScore(this.calculateScore());
    }
    /**
     * X = average rating in last  12 months
     * Y = sales/days since  the product exists
     * Z = quantity of news from the product category on the current day
     * @return  x + y + z
     */
    public BigDecimal calculateScore(){
        return getAverageRatingsInLast12Months()
                .add(getNumOfSalesByDay())
                .add(getNewsByProductCategory());
    }

    /**
     * @return the total of  news by product category considering the date with today
     */

    @JsonIgnore
    public BigDecimal getNewsByProductCategory() {
        long count = category.getNews().stream().filter(news -> {
            LocalDate publishDate = DateUtil.convertCalendarToLocalDate(news.getPublishedAt());
            return LocalDate.now().isEqual(publishDate);
        }).count();
        return BigDecimal.valueOf(count);
    }


    /**
     * Calculate the quantity of sales/days since creating a product.
     * @return total of sales / days since creating a product
     */
    @JsonIgnore
    public BigDecimal getNumOfSalesByDay() {

        Integer numDaysExistingProduct = getNumDaysExistingProduct();
        if(numDaysExistingProduct == 0) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(getSales().size())
               .divide(BigDecimal.valueOf(numDaysExistingProduct));
    }

    private Integer getNumDaysExistingProduct () {
        Period period = Period.between(getCreateAtInLocalDate(), LocalDate.now());
        return period.getDays();
    }


    /**
     * Calculate the product average ratings  in the last twelve moths
     * @return  product average ratings
     */
    @JsonIgnore
    public BigDecimal getAverageRatingsInLast12Months() {
        long totalOfAvaliations = getLastProductAvaliationStream().count();
        if(totalOfAvaliations == 0) {
            return BigDecimal.ZERO;
        }
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
