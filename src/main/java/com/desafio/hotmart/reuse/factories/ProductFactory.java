package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ProductFactory extends BaseFactory<Product> {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductCategoryFactory categoryFactory;

    @Override
    public Product create(boolean save) {
        ProductCategory category =  categoryFactory.create(save);
        return this.create(save, "Produto","Produto Generico", category);
    }

    public Product create(boolean save, String name, String description,  ProductCategory category ) {
        Product p = new Product();
        category.addProduct(p);
        p.setName(name);
        p.setDescription(description);
        p.setCreateAt(Calendar.getInstance());
        if(save) {
            return productRepo.save(p);
        }
        return p;
    }
}
