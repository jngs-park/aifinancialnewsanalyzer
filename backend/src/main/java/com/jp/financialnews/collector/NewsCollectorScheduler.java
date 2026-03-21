package com.jp.financialnews.collector;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsCollectorScheduler {

    private final NewsCollectorService newsCollectorService;

    public NewsCollectorScheduler(NewsCollectorService newsCollectorService) {
        this.newsCollectorService = newsCollectorService;
    }

    @Scheduled(fixedRate = 60000)
    public void collectNvidiaNews() {
        newsCollectorService.collectByKeyword("NVIDIA");
        System.out.println("Scheduled news collection completed for NVIDIA");
    }
}