package com.jp.financialnews.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsArticle, Long> {

    List<NewsArticle> findBySymbolOrderByIdDesc(String symbol);

    List<NewsArticle> findBySymbolAndSentimentOrderByIdDesc(String symbol, String sentiment);
    boolean existsByUrl(String url);
}