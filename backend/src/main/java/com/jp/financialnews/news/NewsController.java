package com.jp.financialnews.news;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public NewsResponse createNews(@RequestBody NewsCreateRequest request) {
        return newsService.createNews(request);
    }

    @GetMapping
    public List<NewsResponse> getNews(@RequestParam String symbol) {
        return newsService.getNewsBySymbol(symbol);
    }
}