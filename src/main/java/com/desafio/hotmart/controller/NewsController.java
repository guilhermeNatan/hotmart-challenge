package com.desafio.hotmart.controller;

import com.desafio.hotmart.controller.requestForms.TopHeadLinesResponseForm;
import com.desafio.hotmart.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/news")
public class NewsController {

    private static final String SUCCESS = "ok";

    @Value("${apiKeys.newsApi}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiOperation apiOperation;




    @Autowired
    private NewsService newsService;

    @GetMapping("/getTopHeadlines")
    public ResponseEntity getProducts() {
        TopHeadLinesResponseForm topHeadLines = restTemplate.getForObject("https://newsapi.org/v2/top-headlines?country=us&apiKey=fb556d6b699e44d9a09e7a7d0f7f7e35", TopHeadLinesResponseForm.class);
        if(topHeadLines.getStatus().equals(SUCCESS) ) {
            topHeadLines.getArticles().forEach(article ->  {

                newsService.insertNewsIfNotExist(article);
            });

        }
        return  apiOperation.transaction(() -> ResponseEntity.ok(topHeadLines));
    }


}
