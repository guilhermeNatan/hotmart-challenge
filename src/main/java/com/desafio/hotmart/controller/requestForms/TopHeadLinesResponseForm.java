package com.desafio.hotmart.controller.requestForms;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TopHeadLinesResponseForm {
    private String status;
    private Integer totalResults;
    private List<ArticleForm> articles;
}

