package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Calendar;

@Getter
@Setter
@Entity
public class News extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String author;
    @Column(columnDefinition = "TEXT")
    private String title ;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String url;
    private Calendar publishedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductCategory category;
}
