package com.jp.financialnews.news;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    public List<NewsResponse> getNewsBySymbol(String symbol) {
        return List.of(
                new NewsResponse(
                        symbol,
                        symbol + " market sentiment improves",
                        symbol + " 관련 시장 심리가 개선되고 있다는 샘플 뉴스 요약입니다."
                ),
                new NewsResponse(
                        symbol,
                        symbol + " sees strong investor attention",
                        symbol + " 관련 투자자 관심이 증가하고 있다는 샘플 뉴스 요약입니다."
                )
        );
    }
}