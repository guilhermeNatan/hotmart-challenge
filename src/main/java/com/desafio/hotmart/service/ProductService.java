package com.desafio.hotmart.service;

import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductService extends BaseService<Product> {

    @Autowired
    private ProductRepo productRepo;

    @Override
    protected JpaRepository<Product, Long> getEntityRepository() {
        return productRepo;
    }

    @Override
    public void validateBeforeSave(Product entity) {

    }
}
