package com.example.pepino.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("LIZARD")
public class Lizard extends Pet {

}
