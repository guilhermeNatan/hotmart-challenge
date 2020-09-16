package com.desafio.hotmart.entity.product;

import com.desafio.hotmart.entity.BaseTest;
import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.repository.ProductRepo;
import com.desafio.hotmart.reuse.factories.ProductFactory;
import lombok.Setter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


@Setter
public class ProductTest extends BaseTest {

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private ProductFactory productFactory;

    @Test
    @Override
    public void createEntityTest() {
        try {
            productFactory.create(true);
        } catch (Exception e) {
            fail("Fail to save a product"+ e.getMessage());
        }
    }

    @Test
    @Override
    public void deleteEntityTest() {
        try {
            Product p = productFactory.create(true);
            productRepo.delete(p);
        }catch (Exception e) {
            fail("Fail to save a product"+ e.getMessage());
        }

    }
}
