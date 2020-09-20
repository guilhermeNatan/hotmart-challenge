package com.desafio.hotmart.controller;

import com.desafio.hotmart.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/getTopHeadlines")
    public ResponseEntity getProducts() {
        newsService.consumingNewsAPI();
        return  ResponseEntity.ok("Success on insert news ");
    }


}
