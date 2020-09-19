package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.News;
import org.springframework.data.repository.cdi.Eager;

@Eager
public interface NewsRepo extends BaseRepo<News> {

    Boolean existsByTitleAndDescription(String title, String description);
}
