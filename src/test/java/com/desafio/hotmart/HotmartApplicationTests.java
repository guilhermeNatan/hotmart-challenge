package com.desafio.hotmart;

import com.desafio.hotmart.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		ProductTest.class,
		SalesmanTest.class,
		SaleTest.class,
		ProductCategoryTest.class,
		BuyerTest.class,
		ProductAvaliation.class
})
public class HotmartApplicationTests {

	@Test
	void contextLoads() {
	}

}
