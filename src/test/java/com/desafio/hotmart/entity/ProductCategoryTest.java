package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.ProductCategoryRepo;
import com.desafio.hotmart.reuse.factories.ProductCategoryFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryTest extends BaseTest {

    @Autowired
    private ProductCategoryFactory productCategoryFactory;

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Override
    public void createEntityTest() {
        try{
            final boolean SAVE = true;
            productCategoryFactory.create(SAVE) ;
        } catch (Exception e) {
            fail("Fail on persist a product category" + e.getMessage());
        }
    }

    @Override
    public void deleteEntityTest() {
        try {
            final boolean SAVE = true;
            ProductCategory  category = productCategoryFactory.create(SAVE) ;
            productCategoryRepo.delete(category);
        }catch (Exception e) {
            fail("Fail on delet product category"+ e.getMessage());
        }
    }
}
