package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.SaleRepo;
import com.desafio.hotmart.reuse.factories.SaleFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SaleTest extends BaseTest {

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private SaleFactory saleFactory;

    @Override
    public void createEntityTest() {
        try{
            final boolean SAVE = true;
            saleFactory.create(SAVE) ;
        } catch (Exception e) {
            fail("Fail on persist a sale" + e.getMessage());
        }
    }

    @Override
    public void deleteEntityTest() {
        try {
            final boolean SAVE = true;
            Sale salesman = saleFactory.create(SAVE) ;
            saleRepo.delete(salesman);
        }catch (Exception e) {
            fail("Fail on delet sales"+ e.getMessage());
        }
    }
}
