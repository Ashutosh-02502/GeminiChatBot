package com.ai.Gemini.chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnaService {

    @Value("${gemini.api.url}")
    private String GeminiApiUrl;

    @Value("${gemini.api.key}")
    private String GeminiApiKey;

    private final WebClient webClient;

    public QnaService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }


    public String getAnswer(String question) {

        Map<String , Object> requestBody = Map.of(
                "contents" , new Object[]{
                        Map.of("parts", new Object[]{
                                        Map.of("text", question)
                                })
                });

        //Make API call
         String responce = webClient.post()
                 .uri(GeminiApiUrl + GeminiApiKey)
                 .header("Content-Type", "application/json")
                 .bodyValue(requestBody)
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();


        return responce;
    }
}
