package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Product;
import org.springframework.data.repository.cdi.Eager;

import java.util.List;

@Eager
public interface ProductRepo extends BaseRepo<Product> {

    List<Product> findAllByName(String name);

    Product findFirstByNameLike(String name);

}
