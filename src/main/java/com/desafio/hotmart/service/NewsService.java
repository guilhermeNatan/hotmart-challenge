package com.desafio.hotmart.service;

import com.desafio.hotmart.controller.requestForms.ArticleForm;
import com.desafio.hotmart.controller.requestForms.TopHeadLinesResponseForm;
import com.desafio.hotmart.entity.News;
import com.desafio.hotmart.entity.ProductCategory;
import com.desafio.hotmart.repository.NewsRepo;
import com.desafio.hotmart.reuse.factories.NewsFactory;
import com.desafio.hotmart.reuse.util.DateUtil;
import com.desafio.hotmart.reuse.util.RequestStatus;
import com.desafio.hotmart.schedulers.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
public class NewsService extends BaseService<News>{

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final String TOP_HEADLINES_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsFactory newsFactory;


    @Value("${apiKeys.newsApi}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Override
    protected JpaRepository<News, Long> getEntityRepository() {
        return newsRepo;
    }


    @Override
    public void validateBeforeSave(News entity) {

    }

    public void insertNewsIfNotExist(ArticleForm article, ProductCategory category) {
        if (DateUtil.isToday(article.getPublishedAt()) && !existingNews(article)) {
               newsFactory.createNewsFromArticle(true, article, category);
               productService.updateScoreByCategory(category);
        }
    }

    private boolean existingNews(ArticleForm article) {
        return newsRepo.existsByTitleAndDescription(article.getTitle(), article.getDescription());
    }

    public void consumingNewsAPI() {
        logger.info("Consuming News API :: Execution Time - {}",
                dateTimeFormatter.format(LocalDateTime.now()) );
        productCategoryService.findAll().forEach(category -> {
            TopHeadLinesResponseForm topHeadLines = restTemplate.getForObject(
                    TOP_HEADLINES_URL + apiKey + "&category=" + category.getName(),
                    TopHeadLinesResponseForm.class);
            if(RequestStatus.SUCCESS.getDescricao().equals(topHeadLines.getStatus()) ) {
                topHeadLines.getArticles().forEach(article ->  {
                    insertNewsIfNotExist(article, category);
                });
            }
        } );
    }


    public void removeOldNews() {
        logger.info("Removing old news- {}", dateTimeFormatter.format(LocalDateTime.now()) );
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, -2);
        newsRepo.removeAllByPublishedAtBefore(today);
    }
}
