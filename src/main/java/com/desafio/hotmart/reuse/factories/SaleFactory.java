package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.Buyer;
import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.entity.Sale;
import com.desafio.hotmart.entity.Salesman;
import com.desafio.hotmart.repository.SaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleFactory extends BaseFactory<Sale>{

    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private BuyerFactory buyerFactory;

    @Autowired
    private  SalesmanFactory salesmanFactory;

    @Override
    public Sale create(boolean save) {
        Product product = productFactory.create(save);
        Buyer buyer = buyerFactory.create(save);
        Salesman salesman = salesmanFactory.create(save);
        return this.create(save, product, salesman, buyer);
    }

    public Sale create(boolean save, Product product, Salesman salesman, Buyer buyer) {
        Sale sale = new Sale();
        sale.setBuyer(buyer);
        sale.setSalesman(salesman);
        sale.setProduct(product);
        if(save) {
            return saleRepo.save(sale);
        }
        return sale;
    }
}
