package com.desafio.hotmart.schedulers;

import com.desafio.hotmart.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Value("${apiKeys.newsApi}")
    private String apiKey;

    @Autowired
    private NewsService newsService;

    /**
     * Consume news api each 6 hours
     */
    @Scheduled(cron = "0 0 0/6 1/1 * ?")
    public void consumingNewsAPIScheduler() {
        newsService.consumingNewsAPI();
    }

    /**
     * Every Monday at 2am removes news that was published more than 2 days ago
     */
    @Scheduled(cron = "0 0 2 ? * MON")
    public void removeOldNews(){
        newsService.removeOldNews();
    }



}
