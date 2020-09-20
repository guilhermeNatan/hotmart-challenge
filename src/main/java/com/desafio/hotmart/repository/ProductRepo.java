package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Product;
import com.desafio.hotmart.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;


@Eager
public interface ProductRepo extends BaseRepo<Product> {

    @Query("select p from Product p where name like %?1%  order by p.score desc, p.name asc , p.category.name asc ")
    Page<Product> findByNameOrderByScoreDescNameAscCategoryAsc(String name, Pageable pageable);

    List<Product> findAllByCategory(ProductCategory category);

}
