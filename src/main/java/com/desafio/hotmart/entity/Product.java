package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {
    @Length(max = 100)
    @NotNull
    private String name;

    @Length(max = 500)
    @NotNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProductCategory category;
}
