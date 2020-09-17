package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Product;
import org.springframework.data.repository.cdi.Eager;


@Eager
public interface ProductRepo extends BaseRepo<Product> {


}
