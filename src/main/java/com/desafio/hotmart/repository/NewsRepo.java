package com.desafio.hotmart.repository;

import com.desafio.hotmart.entity.News;
import org.springframework.data.repository.cdi.Eager;

import java.util.Calendar;

@Eager
public interface NewsRepo extends BaseRepo<News> {

    Boolean existsByTitleAndDescription(String title, String description);

    Long removeAllByPublishedAtBefore(Calendar date);
}
