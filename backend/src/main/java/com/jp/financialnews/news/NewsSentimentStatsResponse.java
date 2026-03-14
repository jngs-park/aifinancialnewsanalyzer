package com.jp.financialnews.news;

public class NewsSentimentStatsResponse {

    private String symbol;
    private double averageScore;
    private long positiveCount;
    private long negativeCount;
    private long neutralCount;
    private long totalCount;

    public NewsSentimentStatsResponse(
            String symbol,
            double averageScore,
            long positiveCount,
            long negativeCount,
            long neutralCount,
            long totalCount
    ) {
        this.symbol = symbol;
        this.averageScore = averageScore;
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
        this.neutralCount = neutralCount;
        this.totalCount = totalCount;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public long getPositiveCount() {
        return positiveCount;
    }

    public long getNegativeCount() {
        return negativeCount;
    }

    public long getNeutralCount() {
        return neutralCount;
    }

    public long getTotalCount() {
        return totalCount;
    }
}