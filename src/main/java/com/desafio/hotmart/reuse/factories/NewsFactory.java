package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.controller.requestForms.ArticleForm;
import com.desafio.hotmart.entity.News;
import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class NewsFactory extends BaseFactory<News> {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private ProductCategoryFactory categoryFactory;

    @Override
    public News create(boolean save) {
        ProductCategory category = categoryFactory.create(save);
        ArticleForm article = new ArticleForm();
        article.setAuthor("Guilherme");
        article.setContent("Lorem ipsum ipsum lorem");
        article.setPublishedAt(Calendar.getInstance());
        article.setTitle("A news title");
        return this.createNewsFromArticle(save, article, category);
    }

    public News createNewsFromArticle(boolean save, ArticleForm article, ProductCategory category) {
        News news = new News();
        news.setAuthor(article.getAuthor());
        news.setPublishedAt(article.getPublishedAt());
        news.setDescription(article.getDescription());
        news.setUrl(article.getUrl());
        category.addNews(news);
        if(save) {
            return newsRepo.save(news);
        }
        return  news;
    }

}
