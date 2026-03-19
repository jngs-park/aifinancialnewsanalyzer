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
    public List<NewsResponse> collectNews(@RequestParam String keyword) {
        return newsCollectorService.collectByKeyword(keyword);
    }
}