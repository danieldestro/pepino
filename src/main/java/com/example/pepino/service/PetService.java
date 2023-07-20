package com.example.pepino.service;

import java.util.List;
import java.util.Map;

import com.example.pepino.ValidationException;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;

public interface PetService {

    Pet create(Pet pet) throws ValidationException;

    Pet find(Long id);

    List<Pet> findAll();

    long count();

    long count(Specie specie);

    Map<Pet, Integer> countSpecies();
}
