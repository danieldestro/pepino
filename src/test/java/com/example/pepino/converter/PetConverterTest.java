package com.example.pepino.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.pepino.api.data.PetDTO;
import com.example.pepino.model.Color;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;
import com.example.pepino.utils.DataBuilderUtils;

/**
 * Simples exemplo de um teste unitário.
 */
public class PetConverterTest {

    PetConverter converter = new PetConverter();

    @Test
    void test_to_error() {
        assertThrows(NullPointerException.class, () -> {
            converter.to((PetDTO) null);
        });
        assertThrows(NullPointerException.class, () -> {
            converter.to(new PetDTO());
        });
        assertThrows(NullPointerException.class, () -> {
            List<PetDTO> list = null;
            converter.to(list);
        });
    }

    @Test
    void test_to() {
        final Long ID = 1L;
        PetDTO dto = DataBuilderUtils.buildDTO(ID);
        Pet pet = converter.to(dto);
        testAssertionsPet(pet, ID);
    }

    @Test
    void test_to_list() {
        final Long ID = 1L;
        PetDTO dto = DataBuilderUtils.buildDTO(ID);
        List<PetDTO> list = new ArrayList<>();
        list.add(dto);
        List<Pet> pets = converter.to(list);
        assertNotNull(pets);
        assertThat(pets.size()).isEqualTo(1);
        testAssertionsPet(pets.get(0), ID);
    }

    @Test
    void test_to_list_empty() {
        List<PetDTO> list = new ArrayList<>();
        List<Pet> pets = converter.to(list);
        assertNotNull(pets);
        assertThat(pets.size()).isEqualTo(0);
    }

    void testAssertionsPet(Pet pet, Long ID) {
        assertNotNull(pet);
        assertThat(pet.getId()).isEqualTo(ID);
        assertThat(pet.getName()).isEqualTo("Frajola");
        assertThat(pet.getColor()).isEqualTo(Color.BLACK);
        assertThat(pet.getWeight()).isEqualTo(10);
        assertType(pet, Specie.CAT);
    }

    @Test
    void test_from_error() {
        assertThrows(NullPointerException.class, () -> {
            converter.from((Pet) null);
        });
        assertThrows(NullPointerException.class, () -> {
            List<Pet> list = null;
            converter.from(list);
        });
    }

    @Test
    void test_from() {
        final Long ID = 1L;
        Pet pet = DataBuilderUtils.buildPet(ID, Specie.DOG);
        PetDTO dto = converter.from(pet);
        assertNotNull(pet);
        testAssertionsPetDTO(dto, ID);
    }

    @Test
    void test_from_list() {
        final Long ID = 1L;
        Pet pet = DataBuilderUtils.buildPet(ID, Specie.DOG);
        List<Pet> list = new ArrayList<>();
        list.add(pet);
        List<PetDTO> dtos = converter.from(list);
        assertNotNull(dtos);
        assertThat(dtos.size()).isEqualTo(1);
        testAssertionsPetDTO(dtos.get(0), ID);
    }

    @Test
    void test_from_list_empty() {
        List<Pet> list = new ArrayList<>();
        List<PetDTO> dtos = converter.from(list);
        assertNotNull(dtos);
        assertThat(dtos.size()).isEqualTo(0);
    }

    void testAssertionsPetDTO(PetDTO dto, Long ID) {
        assertNotNull(dto);
        assertThat(dto.getId()).isEqualTo(ID);
        assertThat(dto.getName()).isEqualTo("Caramelo");
        assertThat(dto.getColor()).isEqualTo(Color.BROWN);
        assertThat(dto.getWeight()).isEqualTo(15);
        assertThat(dto.getSpecie()).isEqualTo(Specie.DOG.toString());
    }

    void assertType(Pet pet, Specie specie) {
        assertThat(pet.getClass().getSimpleName().toUpperCase()).isEqualTo(specie.name());
    }
}
