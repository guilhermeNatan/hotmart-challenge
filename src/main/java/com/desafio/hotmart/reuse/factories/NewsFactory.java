package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.controller.requestForms.ArticleForm;
import com.desafio.hotmart.entity.News;
import com.desafio.hotmart.repository.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class NewsFactory extends BaseFactory<News> {

    @Autowired
    private NewsRepo newsRepo;


    @Override
    public News create(boolean save) {

        ArticleForm article = new ArticleForm();
        article.setAuthor("Guilherme");
        article.setContent("Lorem ipsum ipsum lorem");
        article.setPublishedAt(Calendar.getInstance());
        article.setTitle("A news title");
        return this.createNewsFromArticle(save, article);
    }

    public News createNewsFromArticle(boolean save, ArticleForm article) {
        News news = new News();
        news.setAuthor(article.getAuthor());
        news.setPublishedAt(article.getPublishedAt());
        news.setContent(article.getContent());
        news.setDescription(article.getDescription());
        news.setUrl(article.getUrl());
        news.setUrlToImage(article.getUrlToImage());
        if(save) {
            return newsRepo.save(news);
        }
        return  news;
    }

}
