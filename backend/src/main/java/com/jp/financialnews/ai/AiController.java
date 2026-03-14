package com.jp.financialnews.ai;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/api/ai/analyze")
    public AiAnalyzeResponse analyze(@RequestParam String text) {
        return aiService.analyze(text);
    }
}