package com.example.pepino.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pepino.model.ValueCount;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class CustomPetRepository {

    @Autowired
    private EntityManager em;

    public List<ValueCount> countGroupBySpecie() {
        final String sql = "select specie value, count(*) as count from pet group by specie";
        Query query = em.createNativeQuery(sql);
        List<?> results = query.getResultList();
        results.stream().forEach(System.out::println);
        //results.stream().map(r -> new ValueCount(r.))
        return null;
    }
}