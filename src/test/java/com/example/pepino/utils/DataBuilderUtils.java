package com.example.pepino.utils;

import com.example.pepino.api.data.PetDTO;
import com.example.pepino.model.Cat;
import com.example.pepino.model.Color;
import com.example.pepino.model.Dog;
import com.example.pepino.model.Lizard;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;

public class DataBuilderUtils {

    public static PetDTO buildDTO(Long id) {
        PetDTO dto = new PetDTO();
        dto.setId(id);
        dto.setName("Frajola");
        dto.setColor(Color.BLACK);
        dto.setWeight(10);
        dto.setSpecie(Specie.CAT.toString());
        return dto;
    }

    public static Pet buildPet(Long id, Specie specie) {
        Pet pet = null;
        switch (specie) {
            case CAT:
                pet = new Cat();
                break;
            case DOG:
                pet = new Dog();
                break;
            case LIZARD:
                pet = new Lizard();
                break;
        }
        pet.setId(id);
        pet.setName("Caramelo");
        pet.setColor(Color.BROWN);
        pet.setWeight(15);
        return pet;
    }
}
