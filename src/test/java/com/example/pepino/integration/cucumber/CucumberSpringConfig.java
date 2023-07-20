package com.example.pepino.integration.cucumber;

import java.io.InputStream;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import com.example.pepino.Application;
import com.example.pepino.util.JsonUtil;

import io.cucumber.spring.CucumberContextConfiguration;

@ContextConfiguration
@CucumberContextConfiguration
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfig {

    @LocalServerPort
    protected int port;

    public int getPort() {
        return port;
    }

    protected <T> T loadFromResource(String filename, Class<T> type) throws Exception {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("json/" + filename);) {
            return JsonUtil.fromJsonWithJackson(input, type);
        }
    }
}
