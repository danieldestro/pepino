package com.example.pepino.service;

import java.util.List;
import java.util.Map;

import com.example.pepino.ValidationException;
import com.example.pepino.model.Pet;

public interface PetService {

    Pet create(Pet pet) throws ValidationException;

    Pet find(Long id);

    List<Pet> findAll();

    long count();

    Map<Pet, Integer> countSpecies();
}
