package com.desafio.hotmart.controller.requestForms;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class ArticleForm {
    private SourceResponseForm source;
    private String author;
    private String title ;
    private String description;
    private String url;
    private String urlToImage;
    private Calendar publishedAt;
    private String content;
}
