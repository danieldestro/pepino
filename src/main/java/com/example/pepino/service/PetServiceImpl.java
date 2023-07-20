package com.example.pepino.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pepino.ValidationException;
import com.example.pepino.data.CustomPetRepository;
import com.example.pepino.data.PetRepository;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository repository;

    @Autowired
    private CustomPetRepository customRepository;

    @Override
    public Pet create(Pet pet) throws ValidationException {
        if (!pet.isNew()) {
            throw new ValidationException("pet cannot have ID");
        }
        if (!pet.validate()) {
            throw new ValidationException("pet data is invalid");
        }
        return repository.save(pet);
    }

    @Override
    public Pet find(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Pet> findAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long count(Specie specie) {
        return repository.countGroupBySpecie(specie.name());
    }

    @Override
    public Map<Pet, Integer> countSpecies() {
        Map<Pet, Integer> counts = null;
        customRepository.countGroupBySpecie();
        return counts;
    }
}
