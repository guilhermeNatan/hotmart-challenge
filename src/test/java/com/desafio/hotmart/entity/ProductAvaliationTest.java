package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.ProductAvaliationRepo;
import com.desafio.hotmart.reuse.factories.ProductAvaliationFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductAvaliationTest extends BaseTest {

    @Autowired
    private ProductAvaliationFactory productAvaliationFactory;

    @Autowired
    private ProductAvaliationRepo productAvaliationRepo;

    @Override
    public void createEntityTest() {
        try{
            final boolean SAVE = true;
            productAvaliationFactory.create(SAVE) ;
        } catch (Exception e) {
            fail("Fail on persist a product avaliation" + e.getMessage());
        }
    }

    @Override
    public void deleteEntityTest() {
        try {
            final boolean SAVE = true;
            ProductAvaliation  category = productAvaliationFactory.create(SAVE) ;
            productAvaliationRepo.delete(category);
        }catch (Exception e) {
            fail("Fail on delet product avaliation"+ e.getMessage());
        }
    }
}
