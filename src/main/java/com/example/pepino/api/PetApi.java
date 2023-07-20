package com.example.pepino.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pepino.ValidationException;
import com.example.pepino.api.data.PetDTO;
import com.example.pepino.converter.PetConverter;
import com.example.pepino.model.Pet;
import com.example.pepino.model.Specie;
import com.example.pepino.service.PetService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseBody
@RestController
@RequestMapping(PetApi.URI)
public class PetApi {

    public static final String URI = "/api/pets";

    @Autowired
    private PetService service;

    @Autowired
    private PetConverter converter;

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> find(@PathVariable("id") Long id) {
        Pet pet = service.find(id);
        if (pet != null) {
            return ResponseEntity.ok(converter.from(pet));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> countAll() {
        return ResponseEntity.ok(converter.from(service.findAll()));
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody PetDTO pet) {
        try {
            return ResponseEntity.ok(service.create(converter.to(pet)));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(@RequestParam(name = "specie", required = false) Specie specie) {
        log.info("count specie: " + specie);
        Long count = specie == null ? service.count() : service.count(specie);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/species")
    public ResponseEntity<Map<Pet, Integer>> countSpecies() {
        Map<Pet, Integer> counts = service.countSpecies();
        return ResponseEntity.ok(counts);
    }
}
