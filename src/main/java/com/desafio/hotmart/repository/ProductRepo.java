package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;


@Eager
public interface ProductRepo extends BaseRepo<Product> {

    @Query("select p from Product p where name like %?1%")
    Page<Product> findByNameOrderByNameAsc(String name, Pageable pageable);

}
