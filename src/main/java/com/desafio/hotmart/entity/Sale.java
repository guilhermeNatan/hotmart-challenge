package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale extends BaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Salesman salesman;


    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Buyer buyer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


}
