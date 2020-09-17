package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.Buyer;
import com.desafio.hotmart.repository.BuyerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyerFactory extends BaseFactory<Buyer> {

    @Autowired
    private BuyerRepo buyerRepo;

    @Override
    public Buyer create(boolean save) {
        return this.create(save, "Guilherme");
    }


    public Buyer create(boolean save, String name) {

        Buyer buyer = new Buyer();
        buyer.setName(name);
        if(save) {
            return buyerRepo.save(buyer);
        }
        return buyer;
    }
}
