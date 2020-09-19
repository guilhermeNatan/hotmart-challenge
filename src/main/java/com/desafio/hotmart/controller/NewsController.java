package com.desafio.hotmart.controller;

import com.desafio.hotmart.controller.requestForms.TopHeadLinesResponseForm;
import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.reuse.util.RequestStatus;
import com.desafio.hotmart.service.NewsService;
import com.desafio.hotmart.service.ProductCategoryService;
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

    private static final String TOP_HEADLINES_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";

    @Value("${apiKeys.newsApi}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApiOperation apiOperation;

    @Autowired
    private NewsService newsService;

    @Autowired
   private ProductCategoryService productCategoryService;

    @GetMapping("/getTopHeadlines")
    public ResponseEntity getProducts() {
        productCategoryService.findAll().stream().forEach(category -> {
            TopHeadLinesResponseForm topHeadLines = restTemplate.getForObject(TOP_HEADLINES_URL + apiKey + "&category=" + category.getName(),
                    TopHeadLinesResponseForm.class);
            if(topHeadLines.getStatus().equals(RequestStatus.SUCCESS.getDescricao()) ) {
                topHeadLines.getArticles().forEach(article ->  {
                    newsService.insertNewsIfNotExist(article, category);
                });
            }
        } );
        return  apiOperation.transaction(() -> ResponseEntity.ok("Success on insert news "));
    }


}
