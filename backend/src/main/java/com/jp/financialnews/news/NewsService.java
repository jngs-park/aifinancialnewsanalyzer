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

        NewsArticle article = NewsArticle.builder()
                .symbol(request.getSymbol())
                .title(request.getTitle())
                .summary(request.getSummary())
                .url(null)
                .sentiment(aiResult.getSentiment())
                .score(aiResult.getScore())
                .build();

        NewsArticle saved = newsRepository.save(article);
        return NewsResponse.from(saved);
    }

    public List<NewsResponse> getNewsBySymbol(String symbol) {
        return newsRepository.findBySymbolOrderByIdDesc(symbol)
                .stream()
                .map(NewsResponse::from)
                .toList();
    }

    public List<NewsResponse> getNewsBySymbolAndSentiment(String symbol, String sentiment) {
        return newsRepository.findBySymbolAndSentimentOrderByIdDesc(symbol, sentiment)
                .stream()
                .map(NewsResponse::from)
                .toList();
    }

    public NewsSentimentStatsResponse getSentimentStats(String symbol) {
        List<NewsArticle> articles = newsRepository.findBySymbolOrderByIdDesc(symbol);

        long totalCount = articles.size();
        long positiveCount = articles.stream().filter(a -> "positive".equals(a.getSentiment())).count();
        long negativeCount = articles.stream().filter(a -> "negative".equals(a.getSentiment())).count();
        long neutralCount = articles.stream().filter(a -> "neutral".equals(a.getSentiment())).count();

        double averageScore = articles.stream()
                .mapToInt(NewsArticle::getScore)
                .average()
                .orElse(0.0);

        return new NewsSentimentStatsResponse(
                symbol,
                averageScore,
                positiveCount,
                negativeCount,
                neutralCount,
                totalCount
        );
    }

    public List<NewsSentimentStatsResponse> getLlmSentimentStatsComparison() {
        return List.of(
                getSentimentStats("OPENAI"),
                getSentimentStats("GEMINI"),
                getSentimentStats("CLAUDE")
        );
    }
}