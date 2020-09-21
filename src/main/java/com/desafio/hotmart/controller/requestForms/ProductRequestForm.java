package com.desafio.hotmart.controller.requestForms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class ProductRequestForm {

    @NotBlank(message = "{error.product.category}")
    private String name;
    @NotBlank(message = "{error.product.description}" )
    private String description;
    private Long categoryId;
}
