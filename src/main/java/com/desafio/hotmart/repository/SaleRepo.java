package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Sale;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface SaleRepo extends BaseRepo<Sale> {
}
