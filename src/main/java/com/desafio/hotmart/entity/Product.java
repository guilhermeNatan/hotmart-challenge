package com.desafio.hotmart.entity;

import com.desafio.hotmart.reuse.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {


    private String name;
    private String description;
    private String createAt;
}