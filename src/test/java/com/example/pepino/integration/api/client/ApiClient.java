package com.example.pepino.integration.api.client;

import org.springframework.boot.test.web.server.LocalServerPort;

public abstract class ApiClient {

    private static final String SERVER_URL = "http://localhost";

    @LocalServerPort
    protected int port;

    public int getPort() {
        return port;
    }

    public String getEndpoint() {
        return SERVER_URL + ":" + getPort();
    }
}