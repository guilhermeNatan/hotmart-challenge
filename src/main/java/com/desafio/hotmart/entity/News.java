package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Calendar;

@Getter
@Setter
@Entity
public class News extends BaseEntity {

    private String author;
    private String title ;
    private String description;
    private String url;
    private String urlToImage;
    private Calendar publishedAt;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCategory category;
}
