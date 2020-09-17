package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.Salesman;
import com.desafio.hotmart.repository.SalesmanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesmanFactory  extends BaseFactory<Salesman>{
    @Autowired
    private SalesmanRepo salesmanRepo;

    @Override
    public Salesman create(boolean save) {
        return this.create(save, "Vader");
    }


    public Salesman create(boolean save, String name) {
        Salesman saleman = new Salesman();
        saleman.setName(name);
        if(save) {
            return salesmanRepo.save(saleman);
        }
        return saleman;
    }
}
