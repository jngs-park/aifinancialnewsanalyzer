package com.jp.financialnews.ai;

public class AiAnalyzeRequest {

    private String text;

    public AiAnalyzeRequest() {
    }

    public AiAnalyzeRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}