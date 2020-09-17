package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.SalesmanRepo;
import com.desafio.hotmart.reuse.factories.SalesmanFactory;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
public class SalesmanTest extends  BaseTest {


    @Autowired
    private SalesmanRepo salesmanRepo;

    @Autowired
    private SalesmanFactory salesmanFactory;

    @Override
    public void createEntityTest() {
        try{
            final boolean SAVE = true;
            salesmanFactory.create(SAVE) ;
        } catch (Exception e) {
            fail("Fail on persist a salesman" + e.getMessage());
        }
    }

    @Override
    public void deleteEntityTest() {
        try {
            final boolean SAVE = true;
            Salesman salesman = salesmanFactory.create(SAVE) ;
            salesmanRepo.delete(salesman);
        }catch (Exception e) {
            fail("Fail on delet salesman"+ e.getMessage());
        }
    }
}
