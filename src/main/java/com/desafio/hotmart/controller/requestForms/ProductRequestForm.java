package com.desafio.hotmart.controller.requestForms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
public class ProductRequestForm {

    @NotBlank(message = "{error.product.category}")
    private String name;
    @NotBlank(message = "{error.product.description}" )
    private String description;
    @NotNull(message = "{error.product.category}")
    private Long categoryId;
}
