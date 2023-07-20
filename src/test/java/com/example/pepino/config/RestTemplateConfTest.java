package com.example.pepino.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class RestTemplateConfTest {

    private final String bearerToken;

    public RestTemplateConfTest(@Value("${app.bearer.token}") final String bearerToken) {
        this.bearerToken = bearerToken;
    }

    @Bean("itRestTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + bearerToken);
            return execution.execute(request, body);
        }).build();
    }
}
