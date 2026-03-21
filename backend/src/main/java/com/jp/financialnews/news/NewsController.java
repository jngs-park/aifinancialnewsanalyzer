package com.jp.financialnews.news;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{symbol}")
    public List<NewsResponse> getNewsBySymbol(@PathVariable String symbol) {
        return newsService.getNewsBySymbol(symbol);
    }

    @GetMapping("/{symbol}/{sentiment}")
    public List<NewsResponse> getNewsBySymbolAndSentiment(
            @PathVariable String symbol,
            @PathVariable String sentiment
    ) {
        return newsService.getNewsBySymbolAndSentiment(symbol, sentiment);
    }

    @GetMapping("/stats/{symbol}")
    public NewsSentimentStatsResponse getSentimentStats(@PathVariable String symbol) {
        return newsService.getSentimentStats(symbol);
    }
}