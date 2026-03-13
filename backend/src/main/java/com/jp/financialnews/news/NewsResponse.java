package com.jp.financialnews.news;

public class NewsResponse {

    private Long id;
    private String symbol;
    private String title;
    private String summary;

    public NewsResponse(Long id, String symbol, String title, String summary) {
        this.id = id;
        this.symbol = symbol;
        this.title = title;
        this.summary = summary;
    }

    public static NewsResponse from(NewsArticle article) {
        return new NewsResponse(
                article.getId(),
                article.getSymbol(),
                article.getTitle(),
                article.getSummary()
        );
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }
}