package com.jp.financialnews.news;

public class NewsResponse {

    private Long id;
    private String symbol;
    private String title;
    private String summary;
    private String sentiment;
    private int score;

    public NewsResponse(Long id, String symbol, String title, String summary, String sentiment, int score) {
        this.id = id;
        this.symbol = symbol;
        this.title = title;
        this.summary = summary;
        this.sentiment = sentiment;
        this.score = score;
    }

    public static NewsResponse from(NewsArticle article) {
        return new NewsResponse(
                article.getId(),
                article.getSymbol(),
                article.getTitle(),
                article.getSummary(),
                article.getSentiment(),
                article.getScore()
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

    public String getSentiment() {
        return sentiment;
    }

    public int getScore() {
        return score;
    }
}