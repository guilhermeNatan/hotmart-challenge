package com.desafio.hotmart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Buyer extends BaseEntity {

    @NotNull
    @Length(max = 150)
    private String name;

}
