package com.desafio.hotmart.entity;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Classe abstrata de teste. Ao extender essa classe,  possvel configurar os metdos setUp
 * e tearDown.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application_teste.properties")
public abstract class BaseTest extends TestCase
{

  @Test
  public abstract void createEntityTest();

  @Test
  public abstract void deleteEntityTest();
}
