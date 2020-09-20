package com.desafio.hotmart.controller.requestForms;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;

@Getter
@Setter
public class ProductForm {
    private Long id;
    private String name;
    private String description;
    private Calendar createAt;
    private BigDecimal score;
}
