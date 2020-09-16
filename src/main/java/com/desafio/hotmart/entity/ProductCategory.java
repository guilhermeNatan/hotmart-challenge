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
public class ProductCategory  extends BaseEntity {

    private String name;
}
