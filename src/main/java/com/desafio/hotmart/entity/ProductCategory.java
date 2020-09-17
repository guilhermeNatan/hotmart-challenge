package com.desafio.hotmart.entity;

import com.desafio.hotmart.reuse.factories.util.CollectionHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class ProductCategory  extends BaseEntity {
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> products;

    public void addProduct(Product p) {
        p.setCategory(this);
        getProducts().add(p);
    }

    public List<Product> getProducts() {
        products = CollectionHelper.instantiateListIfNecessary(products);
        return products;
    }

}


