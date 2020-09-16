package com.desafio.hotmart.entity;

import com.desafio.hotmart.reuse.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    private Salesman salesman;


    @OneToOne(fetch = FetchType.LAZY)
    private Buyer buyer;

    @OneToOne(fetch = FetchType.LAZY)
    private Product product;
}
