package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryFactory  extends BaseFactory<ProductCategory> {
    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCategory create(boolean save) {
        return this.create(save, "general");
    }


    public ProductCategory create(boolean save, String name) {
        ProductCategory category = new ProductCategory();
        category.setName(name);
        if(save) {
            return productCategoryRepo.save(category);
        }
        return category;
    }
}
