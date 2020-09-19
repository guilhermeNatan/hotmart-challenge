package com.desafio.hotmart.entity;

import com.desafio.hotmart.repository.NewsRepo;
import com.desafio.hotmart.reuse.factories.NewsFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class NewsTest extends BaseTest {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsFactory newsFactory;

    @Override
    public void createEntityTest() {
        try{
            final boolean SAVE = true;
            newsFactory.create(SAVE) ;
        } catch (Exception e) {
            fail("Fail on persist a buyer" + e.getMessage());
        }
    }

    @Override
    public void deleteEntityTest() {
        try {
            final boolean SAVE = true;
            News news = newsFactory.create(SAVE) ;
            newsRepo.delete(news);
        }catch (Exception e) {
            fail("Fail on delete news"+ e.getMessage());
        }
    }
}
