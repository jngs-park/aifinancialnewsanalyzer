package com.jp.financialnews.news;

public class NewsResponse {

    private String symbol;
    private String title;
    private String summary;

    public NewsResponse(String symbol, String title, String summary) {
        this.symbol = symbol;
        this.title = title;
        this.summary = summary;
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