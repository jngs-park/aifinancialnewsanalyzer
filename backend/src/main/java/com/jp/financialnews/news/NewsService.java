package com.jp.financialnews.news;

import com.jp.financialnews.ai.AiAnalyzeResponse;
import com.jp.financialnews.ai.AiService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final AiService aiService;

    public NewsService(NewsRepository newsRepository, AiService aiService) {
        this.newsRepository = newsRepository;
        this.aiService = aiService;
    }

    public NewsResponse createNews(NewsCreateRequest request) {
        String textForAnalysis = request.getTitle() + " " + request.getSummary();

        AiAnalyzeResponse aiResult = aiService.analyze(textForAnalysis);

        NewsArticle article = new NewsArticle(
                request.getSymbol(),
                request.getTitle(),
                request.getSummary(),
                aiResult.getSentiment(),
                aiResult.getScore()
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