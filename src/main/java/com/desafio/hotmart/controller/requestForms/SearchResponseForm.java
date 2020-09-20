package com.desafio.hotmart.controller.requestForms;

import com.desafio.hotmart.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Formulario de busca para area de conhecimento e coleção.
 */
@Getter
@Setter
public class SearchResponseForm
{
  private int page;
  private String termoPesquisado;
  private Calendar dataAtual;
  private List<Product> conteudo = new ArrayList<>();
}
