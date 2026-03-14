package com.jp.financialnews.ai;

import com.jp.financialnews.common.AiServerUnavailableException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class AiService {

    private final RestTemplate restTemplate = new RestTemplate();

    public AiAnalyzeResponse analyze(String text) {
        String url = "http://localhost:8000/analyze";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        AiAnalyzeRequest request = new AiAnalyzeRequest(text);
        HttpEntity<AiAnalyzeRequest> entity = new HttpEntity<>(request, headers);

        try {
            return restTemplate.postForObject(url, entity, AiAnalyzeResponse.class);
        } catch (ResourceAccessException e) {
            throw new AiServerUnavailableException("AI analysis server is not available");
        }
    }
}