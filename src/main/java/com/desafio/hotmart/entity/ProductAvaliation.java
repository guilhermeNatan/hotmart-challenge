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
public class ProductAvaliation extends BaseEntity{

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @NotNull
    private Integer avaliation;
}
