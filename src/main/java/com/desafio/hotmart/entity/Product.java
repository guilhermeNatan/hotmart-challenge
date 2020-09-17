package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {
    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProductCategory category;
}
