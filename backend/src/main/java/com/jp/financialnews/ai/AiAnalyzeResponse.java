package com.jp.financialnews.ai;

public class AiAnalyzeResponse {

    private String sentiment;
    private int score;

    public AiAnalyzeResponse() {
    }

    public String getSentiment() {
        return sentiment;
    }

    public int getScore() {
        return score;
    }
}