package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Salesman;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface SalesmanRepo extends BaseRepo<Salesman>  {
}
