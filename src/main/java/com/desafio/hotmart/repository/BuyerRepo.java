package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.Buyer;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface BuyerRepo  extends BaseRepo<Buyer> {
}
