package com.jp.financialnews.collector;

import com.jp.financialnews.news.NewsResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collector")
public class NewsCollectorController {

    private final NewsCollectorService newsCollectorService;

    public NewsCollectorController(NewsCollectorService newsCollectorService) {
        this.newsCollectorService = newsCollectorService;
    }

    @PostMapping("/news")
    public List<NewsResponse> collectNews(
            @RequestParam String symbol,
            @RequestParam String query
    ) {
        return newsCollectorService.collectNews(symbol, query);
    }

    @PostMapping("/llm")
    public void collectLlmNews() {
        newsCollectorService.collectNews("OPENAI", "OpenAI OR ChatGPT");
        newsCollectorService.collectNews("GEMINI", "Google Gemini");
        newsCollectorService.collectNews("CLAUDE", "Anthropic Claude");
    }
}