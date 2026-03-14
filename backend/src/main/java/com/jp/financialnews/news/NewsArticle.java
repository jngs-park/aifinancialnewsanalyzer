package com.jp.financialnews.news;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news_article")
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String summary;

    @Column(nullable = false)
    private String sentiment;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected NewsArticle() {
    }

    public NewsArticle(String symbol, String title, String summary, String sentiment, int score) {
        this.symbol = symbol;
        this.title = title;
        this.summary = summary;
        this.sentiment = sentiment;
        this.score = score;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}