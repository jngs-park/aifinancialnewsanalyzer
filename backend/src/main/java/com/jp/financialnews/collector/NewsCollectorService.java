package com.jp.financialnews.collector;

import com.jp.financialnews.ai.AiService;
import com.jp.financialnews.collector.dto.NewsApiResponse;
import com.jp.financialnews.news.NewsArticle;
import com.jp.financialnews.news.NewsRepository;
import com.jp.financialnews.news.NewsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsCollectorService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final NewsRepository newsRepository;
    private final AiService aiService;

    @Value("${newsapi.base-url}")
    private String baseUrl;

    @Value("${newsapi.api-key}")
    private String apiKey;

    public NewsCollectorService(NewsRepository newsRepository, AiService aiService) {
        this.newsRepository = newsRepository;
        this.aiService = aiService;
    }

    public List<NewsResponse> collectByKeyword(String keyword) {

        String requestUrl = baseUrl + "?q=" + keyword + "&apiKey=" + apiKey;

        NewsApiResponse response = restTemplate.getForObject(requestUrl, NewsApiResponse.class);

        if (response == null || response.getArticles() == null) {
            throw new RuntimeException("News API response is null");
        }

        return response.getArticles().stream()
                .filter(article -> article.getUrl() != null && !article.getUrl().isBlank())
                .filter(article -> !newsRepository.existsByUrl(article.getUrl()))
                .map(article -> {
                    String title = article.getTitle() != null ? article.getTitle() : "";
                    String description = article.getDescription() != null ? article.getDescription() : "";
                    String textForAi = title + " " + description;

                    var aiResult = aiService.analyze(textForAi);
                    var summaryResult = aiService.summarize(textForAi);

                    NewsArticle entity = NewsArticle.builder()
                            .symbol(keyword)
                            .title(title)
                            .summary(summaryResult.getSummary())
                            .url(article.getUrl())
                            .sentiment(aiResult.getSentiment())
                            .score(aiResult.getScore())
                            .build();

                    return NewsResponse.from(newsRepository.save(entity));
                })
                .collect(Collectors.toList());
    }
}