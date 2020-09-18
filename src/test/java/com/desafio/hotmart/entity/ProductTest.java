package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.ProductRepo;
import com.desafio.hotmart.reuse.factories.*;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Calendar;


@Setter
public class ProductTest extends BaseTest {

    @Autowired
    private ProductRepo productRepo;


    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private SaleFactory saleFactory;

    @Autowired
    private SalesmanFactory salesmanFactory;

    @Autowired
    private BuyerFactory buyerFactory;

    @Autowired
    private ProductAvaliationFactory productAvaliationFactory;

    @Test
    @Override
    public void createEntityTest() {
        try {
            productFactory.create(true);
        } catch (Exception e) {
            fail("Fail to save a product"+ e.getMessage());
        }
    }

    @Test
    @Override
    public void deleteEntityTest() {
        try {
            Product p = productFactory.create(true);
            productRepo.delete(p);
        }catch (Exception e) {
            fail("Fail to save a product"+ e.getMessage());
        }

    }

    /**
     * Objective: Check quantity of sales/days of a product
     */
    @Test
    public void getNumOfSalesByDayTest() {
        final boolean PERSIST_DATA = true;
        final int NUM_OF_SALES = 10;
        final int DAYS_OF_PRODUCT_CREATION = 5;
        Product product = productFactory.create(PERSIST_DATA);
        Salesman salesman = salesmanFactory.create(PERSIST_DATA);
        Buyer buyer = buyerFactory.create(PERSIST_DATA);

        Calendar productCreatAt = Calendar.getInstance();
        productCreatAt.add(Calendar.DAY_OF_YEAR, -DAYS_OF_PRODUCT_CREATION);
        product.setCreateAt(productCreatAt);

        for (int i = 0; i < NUM_OF_SALES; i++) {
            saleFactory.create(PERSIST_DATA, product, salesman, buyer);
        }
        BigDecimal expected = BigDecimal.valueOf(NUM_OF_SALES)
                .divide(BigDecimal.valueOf(DAYS_OF_PRODUCT_CREATION));

        Assert.assertEquals(expected, product.getNumOfSalesByDay());
    }


    /**
     * Objective: Check the calculate of avarage ratings
     */
    @Test
    public void getAverageRatingsInLast12MonthsTest() {
        final boolean PERSIST_DATA = true;
        final int NUM_OF_AVALIATIONS = 10;
        Product product = productFactory.create(PERSIST_DATA);
        int totalAvaliation = 0;

        //This avaliation have be ignoreted becouse it is was created before 12 moths
        Calendar dateBefore12Months = Calendar.getInstance();
        dateBefore12Months.add(Calendar.YEAR, -2);
        ProductAvaliation avaliantionBeforere12Months = productAvaliationFactory.create(PERSIST_DATA, product, 2);
        avaliantionBeforere12Months.setCreateAt(dateBefore12Months);

        // only this avaliations have be accepted for calculate the avarage ratings
        for(int i = 0 ; i< NUM_OF_AVALIATIONS; i++) {
            int avaliation = i%5 ;
            totalAvaliation = totalAvaliation + avaliation;
             productAvaliationFactory.create(PERSIST_DATA, product, avaliation);

        }

        Assert.assertEquals(BigDecimal.valueOf(totalAvaliation)
                .divide(BigDecimal.valueOf(10)),  product.getAverageRatingsInLast12Months());

    }

}
