package com.example.pepino.integration.cucumber.steps;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    /**
     * Skips all SCENARIOS masked with tag @Skip.
     * @param scenario
     */
    @Before("@Skip")
    public void skipScenario(Scenario scenario) {
        System.out.println("SKIP SCENARIO: " + scenario.getName());
        assumeTrue(false);
    }
}
