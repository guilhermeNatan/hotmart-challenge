package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.entity.ProductAvaliation;
import com.desafio.hotmart.repository.ProductAvaliationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductAvaliationFactory extends BaseFactory<ProductAvaliation>{

    @Autowired
    private ProductAvaliationRepo productAvaliationRepo;

    @Autowired
    private ProductFactory productFactory;

    @Override
    public ProductAvaliation create(boolean save) {
        Product product = productFactory.create(save);
        return this.create(save, product, 4);
    }


    public ProductAvaliation create(boolean save, Product product, Integer avaliation) {
        ProductAvaliation productAvaliation = new ProductAvaliation();
        productAvaliation.setAvaliation(avaliation);
        product.addAvaliation(productAvaliation);
        if(save) {
            return  productAvaliationRepo.save(productAvaliation);
        }
        return productAvaliation;
    }
}
