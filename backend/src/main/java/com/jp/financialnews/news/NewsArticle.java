package com.jp.financialnews.news;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
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

    @Column(unique = true, length = 1000)
    private String url;

    @Builder
    public NewsArticle(String symbol, String title, String summary,
                       String url, String sentiment, int score) {
        this.symbol = symbol;
        this.title = title;
        this.summary = summary;
        this.url = url;
        this.sentiment = sentiment;
        this.score = score;
        this.createdAt = LocalDateTime.now();
    }
}