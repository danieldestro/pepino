package com.example.pepino.info;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
//@ScenarioScope
public class PetInfo {

    private String name;
    private int age;
    private String color;
}
