package com.jp.financialnews.news;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public NewsResponse createNews(NewsCreateRequest request) {
        NewsArticle article = new NewsArticle(
                request.getSymbol(),
                request.getTitle(),
                request.getSummary()
        );

        NewsArticle saved = newsRepository.save(article);
        return NewsResponse.from(saved);
    }

    public List<NewsResponse> getNewsBySymbol(String symbol) {
        return newsRepository.findBySymbolOrderByIdDesc(symbol)
                .stream()
                .map(NewsResponse::from)
                .toList();
    }
}