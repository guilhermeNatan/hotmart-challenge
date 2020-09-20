package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.DataBaseVersion;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface DataBaseVersionRepo extends BaseRepo<DataBaseVersion> {

}
