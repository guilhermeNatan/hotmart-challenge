package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.ProductAvaliation;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface ProductAvaliationRepo extends BaseRepo<ProductAvaliation>{
}
