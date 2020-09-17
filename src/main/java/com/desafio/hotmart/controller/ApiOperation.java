package com.desafio.hotmart.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@AllArgsConstructor
@Component
public class ApiOperation
{

  private TransactionTemplate transactionTemplate;

  /**
   * Start a new trasaction filter
   * @param s transaction supplier
   * @return  ResponseEntity
   */
  public <T> T transaction(Supplier<T> s)
  {
    return transactionTemplate.execute((status) -> s.get());
  }

}
