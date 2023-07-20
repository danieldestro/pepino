package com.example.pepino.model;

import com.example.pepino.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name = "pet")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "specie", discriminatorType = DiscriminatorType.STRING)
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double weight;
    private Color color;

    @JsonIgnore
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public boolean validate() {
        return !StringUtils.isEmpty(name) && weight > 0 && color != null;
    }
}
