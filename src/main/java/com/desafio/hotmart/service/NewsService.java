package com.desafio.hotmart.service;

import com.desafio.hotmart.controller.requestForms.ArticleForm;
import com.desafio.hotmart.entity.News;
import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.NewsRepo;
import com.desafio.hotmart.reuse.factories.NewsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class NewsService extends BaseService<News>{

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsFactory newsFactory;



    @Override
    protected JpaRepository<News, Long> getEntityRepository() {
        return newsRepo;
    }

    @Override
    public void validateBeforeSave(News entity) {

    }

    public void insertNewsIfNotExist(ArticleForm article, ProductCategory category) {
        if (!existingNews(article)) {
                newsFactory.createNewsFromArticle(true, article, category);

        }
    }

    private boolean existingNews(ArticleForm article) {
        return newsRepo.existsByTitleAndDescription(article.getTitle(), article.getDescription());
    }
}
