package com.example.pepino.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.pepino.model.Pet;
import com.example.pepino.model.ValueCount;

public interface PetRepository extends CrudRepository<Pet, Long> {

    List<Pet> findAll();

    // @Query(value = "select new com.example.pepino.model.PetCount(p, count(p)) from Pet p group by p.class", nativeQuery = true)
    @Query(value = "select specie as value, count(*) as count from pet group by specie", nativeQuery = true)
    List<ValueCount> countGroupBySpecies();

    @Query(value = "select count(*) from pet where specie=:specie", nativeQuery = true) // group by specie
    long countGroupBySpecie(@Param("specie") String specie);
}
