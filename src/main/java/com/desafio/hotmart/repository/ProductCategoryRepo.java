package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.ProductCategory;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface ProductCategoryRepo extends BaseRepo<ProductCategory>  {
    ProductCategory findFirstByNameLike(String name);


}
