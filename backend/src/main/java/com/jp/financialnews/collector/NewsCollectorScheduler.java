package com.jp.financialnews.collector;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsCollectorScheduler {

    private final NewsCollectorService newsCollectorService;

    public NewsCollectorScheduler(NewsCollectorService newsCollectorService) {
        this.newsCollectorService = newsCollectorService;
    }

    @Scheduled(fixedRate = 3600000)
    public void collectLlmNews() {
        newsCollectorService.collectNews("OPENAI", "OpenAI OR ChatGPT");
        System.out.println("Scheduled news collection completed for OPENAI");

        newsCollectorService.collectNews("GEMINI", "Google Gemini");
        System.out.println("Scheduled news collection completed for GEMINI");

        newsCollectorService.collectNews("CLAUDE", "Anthropic Claude");
        System.out.println("Scheduled news collection completed for CLAUDE");
    }
}