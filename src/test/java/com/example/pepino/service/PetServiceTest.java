package com.example.pepino.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.pepino.ValidationException;
import com.example.pepino.data.PetRepository;
import com.example.pepino.model.Cat;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;
import com.example.pepino.utils.DataBuilderUtils;

/**
 * Teste unitário implementado com o uso de Mock objects.
 */
@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository repository;

    @InjectMocks
    private PetService service = new PetServiceImpl();

    @Test
    void test_create() throws ValidationException {

        final Pet pet = DataBuilderUtils.buildPet(null, Specie.DOG);
        final Pet petReturn = DataBuilderUtils.buildPet(1L, Specie.DOG);
        Mockito.when(repository.save(pet)).thenReturn(petReturn);

        Pet savedPet = service.create(pet);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        Mockito.verify(repository, times(1)).save(pet);
    }

    @Test
    void test_create_erro_ID() throws ValidationException {

        final Pet pet = DataBuilderUtils.buildPet(1L, Specie.DOG);

        assertThrows(ValidationException.class, () -> {
            service.create(pet);
        }, "pet cannot have ID");
    }

    @Test
    void test_create_erro_dados() throws ValidationException {

        final Pet pet = new Cat();

        assertThrows(ValidationException.class, () -> {
            service.create(pet);
        }, "pet data is invalid");
    }
}
