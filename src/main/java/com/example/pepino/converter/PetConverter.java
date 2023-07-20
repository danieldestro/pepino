package com.example.pepino.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.pepino.api.data.PetDTO;
import com.example.pepino.model.Cat;
import com.example.pepino.model.Dog;
import com.example.pepino.model.Lizard;
import com.example.pepino.model.Pet;

@Component
public class PetConverter implements Converter<PetDTO, Pet> {

    @Override
    public Pet to(PetDTO dto) {
        Pet pet = null;

        switch (dto.getSpecie()) {
            case "DOG":
                pet = new Dog();
                break;
            case "CAT":
                pet = new Cat();
                break;
            case "LIZARD":
                pet = new Lizard();
                break;
        }

        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setWeight(dto.getWeight());
        pet.setColor(dto.getColor());

        return pet;
    }

    @Override
    public List<Pet> to(List<PetDTO> dtos) {
        return dtos.stream().map(d -> to(d)).collect(Collectors.toList());
    }

    @Override
    public PetDTO from(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setWeight(pet.getWeight());
        dto.setColor(pet.getColor());
        dto.setSpecie(pet.getClass().getSimpleName().toUpperCase());
        return dto;
    }

    @Override
    public List<PetDTO> from(List<Pet> pets) {
        return pets.stream().map(p -> from(p)).collect(Collectors.toList());
    }
}
