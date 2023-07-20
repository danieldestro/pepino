package com.example.pepino.api.data;

import com.example.pepino.model.Color;

import lombok.Data;

@Data
public class PetDTO {

    private Long id;
    private String name;
    private double weight;
    private Color color;
    private String specie;
}
