package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class ProductFactory extends BaseFactory<Product> {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product create(boolean save) {
        Product p = new Product();
        p.setName("novoProduto");
        p.setDescription("Teste");
        p.setCreateAt(Calendar.getInstance());

        if(save) {
            return productRepo.save(p);
        }

        return p;
    }
}
