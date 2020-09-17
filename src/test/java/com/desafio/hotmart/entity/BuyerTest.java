package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.BuyerRepo;
import com.desafio.hotmart.reuse.factories.BuyerFactory;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
public class BuyerTest   extends BaseTest{


    @Autowired
    private BuyerRepo buyerRepo;

    @Autowired
    private BuyerFactory buyerFactory;

    @Override
    public void createEntityTest() {
        try{
           final boolean SAVE = true;
            buyerFactory.create(SAVE) ;
        } catch (Exception e) {
            fail("Fail on persist a buyer" + e.getMessage());
        }

    }

    @Override
    public void deleteEntityTest() {
        try {
            final boolean SAVE = true;
            Buyer buyer = buyerFactory.create(SAVE) ;
            buyerRepo.delete(buyer);
        }catch (Exception e) {
            fail("Fail on delet buyer"+ e.getMessage());
        }
    }
}
