package com.desafio.hotmart.configuration;

import com.desafio.hotmart.entity.*;
import com.desafio.hotmart.reuse.factories.*;
import com.desafio.hotmart.service.DataBaseVersionService;
import com.desafio.hotmart.service.NewsService;
import com.desafio.hotmart.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@Component
public class DataLoader implements ApplicationRunner {



    private final String[] complements = new String[] {
            "Tv",
            "Lavadoura",
            "Ar condicionado",
            "Computador",
            "Sistema de som",
            "Carro",
            "Telefone",
            "Barbeador",
            "Caneta",
            "Lápis",
            "Notebook",
    };
    private final String[] productNames = new String [] {
            "Cabo de ",
            "Antena de ",
            "Placa de ",
            "Relógio de",
            "Limpador de ",
            "Capa de",
            "Sistema de ",
            "Separador de ",
            "Arrumador de ",
            "Fixador de"
    };

    private final String[] productCategories = new String[]{
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology",
    };



    private final String[] descriptions = new String[] {
            "Aprenda a fazer",
            "Criação de",
            "Faça uma viagem para o exterior usando seus conhecimentos em",
            "Melhore seu curriculo com o curso de",
            "Um maravilhoso ",

    };

    private final String names[] = new String[] {
            "Bofpli",
            "Asel",
            "Bofhu",
            "Glorso",
            "Farim",
            "Xaeriel",
            "Aluilos",
            "Damyaraen",
            "Merien"
    };

    private final String[] lastnames = new String[]{
            "Tinuan",
            "Melanor",
            "Hirtibeo",
            "Souor",
            "Morrien",
            "Neinbu",
            "Plozeagu",
            "Taufo",
            "Fefi"

    };


    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SalesmanFactory salesmanFactory ;

    @Autowired
    private BuyerFactory buyerFactory ;

    @Autowired
    private SaleFactory saleFactory;

    @Autowired
    private ProductAvaliationFactory productAvaliationFactory;

    @Autowired
    private NewsService newsService;

    @Autowired
    private DataBaseVersionService dataBaseVersionService;



    @Override
    public void run(ApplicationArguments args) throws Exception {

        Random rand = new Random();
        final boolean PERSIST = true;
        final int MAX_REGISTERS_BY_CATEGORY = 18;
        DataBaseVersion currentDataVersion = dataBaseVersionService.getCurrentDataVersion();
        if(currentDataVersion==null) {
            dataBaseVersionService.initDataBase();
            Arrays.stream(productCategories).forEach(category -> {
                ProductCategory productCategory = productCategoryService.insertProductCategoryIfNotExist(category);
                for(int i =0 ; i< MAX_REGISTERS_BY_CATEGORY; i++) {
                    String name = productNames[rand.nextInt(this.productNames.length)];
                    String complement = complements[rand.nextInt(this.complements.length)];
                    String description = descriptions[rand.nextInt(this.descriptions.length)];
                    Product product = productFactory.create(PERSIST, name + " " + complement, description + " " + complement ,productCategory);
                    Salesman salesman = salesmanFactory.create(PERSIST, names[rand.nextInt(this.names.length)] +
                            lastnames[rand.nextInt(this.lastnames.length)]);
                    Buyer buyer= buyerFactory.create(PERSIST, lastnames[rand.nextInt(this.lastnames.length)] +
                            names[rand.nextInt(this.names.length)]);
                    saleFactory.create(PERSIST, product, salesman,buyer);
                    productAvaliationFactory.create(PERSIST, product, rand.nextInt(5));
                }
            });

            newsService.consumingNewsAPI();
        }



    }
}
