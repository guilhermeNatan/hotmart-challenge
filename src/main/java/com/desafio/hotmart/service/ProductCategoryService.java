package com.desafio.hotmart.service;

import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.ProductCategoryRepo;
import com.desafio.hotmart.reuse.factories.ProductCategoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryService extends BaseService<ProductCategory> {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Autowired
    private ProductCategoryFactory productCategoryFactory;

    @Override
    protected JpaRepository<ProductCategory, Long> getEntityRepository() {
        return productCategoryRepo;
    }

    @Override
    public void validateBeforeSave(ProductCategory entity) {

    }

    public ProductCategory insertProductCategoryIfNotExist(String categoryName) {
        ProductCategory productCategory = productCategoryRepo.findFirstByNameLike(categoryName);
        if(productCategory==null) {
           return productCategoryFactory.create(true, categoryName);
        }
        return productCategory;
    }
}
