package com.example.pepino.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.pepino.Application;
import com.example.pepino.ValidationException;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;
import com.example.pepino.utils.DataBuilderUtils;

@SpringBootTest(classes = Application.class)
public class PetServiceIT {

    @Autowired
    private PetService service;

    @Test
    void test() throws ValidationException {
        assertCount(0);

        Pet pet = DataBuilderUtils.buildPet(null, Specie.DOG);
        service.create(pet);
        assertNotNull(pet);
        assertNotNull(pet.getId());
        assertThat(service.count()).isEqualTo(1);

        assertCount(1);

        final Long id = pet.getId();
        pet = service.find(id);
        assertNotNull(pet);
        assertNotNull(pet.getId());
        assertThat(pet.getId()).isEqualTo(id);
    }

    @Test
    void test_find_empty() throws ValidationException {
        Pet pet = service.find(9999L);
        assertNull(pet);
    }

    void assertCount(int qty) {
        List<Pet> all = service.findAll();
        assertNotNull(all);
        assertThat(all.size()).isEqualTo(qty);
        assertThat(service.count()).isEqualTo(qty);
    }
}
