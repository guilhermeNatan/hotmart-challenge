package com.desafio.hotmart.reuse.factories;

import com.desafio.hotmart.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseFactory<T extends BaseEntity> {

    /**
     * @param save true if persist object
     * @return a new object
     */
    public abstract  T create(boolean save);

}
