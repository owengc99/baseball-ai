package com.owengc.baseball_ai.nlquery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@Component
public class GeminiSqlGenerator implements SqlGenerator {

    private final PromptBuilder promptBuilder;
    private final RestClient restClient;
    private final JsonMapper jsonMapper;

    private final String apiKey;
    private final String model;

    public GeminiSqlGenerator(PromptBuilder promptBuilder,
                              JsonMapper jsonMapper,
                              @Value("${gemini.api-key}") String apiKey,
                              @Value("${gemini.model}") String model,
                              @Value("${gemini.url}") String url) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GEMINI_API_KEY is not set. The natural language query endpoint "
                            + "cannot function without it.");
        }

        this.promptBuilder = promptBuilder;
        this.jsonMapper = jsonMapper;
        this.apiKey = apiKey;
        this.model = model;
        this.restClient = RestClient.builder().baseUrl(url).build();
    }

    @Override
    public String generateSql(String question) {
        String prompt = promptBuilder.build(question);

        Map<String, Object> body = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        String response = restClient.post()
                .uri("/{model}:generateContent", model)
                .header("x-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .body(String.class);

        return extractText(response);
    }

    private String extractText(String json) {
        try {
            JsonNode root = jsonMapper.readTree(json);
            String text = root.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();
            return text.trim();
        } catch (Exception e) {
            throw new IllegalStateException("Could not parse Gemini response", e);
        }
    }
}