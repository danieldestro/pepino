package com.example.pepino.integration.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.pepino.api.data.PetDTO;
import com.example.pepino.data.PetRepository;
import com.example.pepino.integration.api.client.PetApiClient;
import com.example.pepino.integration.cucumber.CucumberSpringConfig;
import com.example.pepino.model.Color;
import com.example.pepino.model.Specie;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StepsDefinitions extends CucumberSpringConfig {

    @Autowired
    private PetRepository repository;

    @Autowired
    private PetApiClient petApiClient;

    @Given("there are no pets")
    public void step() {
        log.info("STEP: there are no pets");
        repository.deleteAll();
    }

    @When("I do nothing")
    public void nothing() {
        log.info("STEP: I do nothing");
    }

    @When("I create a {specieType} with data")
    public void create(Specie specie, PetDTO dto) {
        log.info("STEP: I create a {specieType} with data");
        dto.setSpecie(specie.toString());
        petApiClient.create(dto);
    }

    @When("there are {long} pets")
    public void countPets(long count) {
        log.info("STEP: there are {long} pets");
        assertThat(count).isEqualTo(repository.count());
    }

    @When("I can count {int} pets")
    public void countPetsApi(int count) {
        log.info("STEP: I can count {int} pets");
        long apiCount = petApiClient.count().getBody();
        assertThat(count).isEqualTo(apiCount);
    }

    @ParameterType(name = "colorType", value = "[A-Z]+")
    public Color colorType(String color) {
        return Color.valueOf(color);
    }

    @ParameterType(name = "specieType", value = "DOG|CAT|LIZARD")
    public Specie specieType(String specie) {
        return Specie.valueOf(specie);
    }

    @DataTableType
    public PetDTO dataTablePetDTO(Map<String, String> data) {
        PetDTO dto = new PetDTO();
        dto.setName(data.get("name"));
        dto.setColor(colorType(data.get("color")));
        dto.setWeight(Double.valueOf(data.get("weight")));
        return dto;
    }
}
