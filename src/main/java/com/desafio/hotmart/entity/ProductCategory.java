package com.desafio.hotmart.entity;

import com.desafio.hotmart.reuse.util.CollectionHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;



@Entity
@NoArgsConstructor
public class ProductCategory  extends BaseEntity {

    @Getter
    @Setter
    @NotNull
    @Length(max = 100)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<News> news;



    public void addProduct(Product p) {
        p.setCategory(this);
        getProducts().add(p);
    }

    public List<Product> getProducts() {
        products = CollectionHelper.instantiateListIfNecessary(products);
        return products;
    }

    public void addNews(News news) {
        news.setCategory(this);
        getNews().add(news);
    }

    public List<News> getNews() {
        news = CollectionHelper.instantiateListIfNecessary(news);
        return news;
    }

}


