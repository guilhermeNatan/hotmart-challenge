package com.desafio.hotmart.reuse.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionHelper {

    public static  <E> List<E> instantiateListIfNecessary(List<E> list) {
        return list != null ? list : new ArrayList<E>();
    }
}
