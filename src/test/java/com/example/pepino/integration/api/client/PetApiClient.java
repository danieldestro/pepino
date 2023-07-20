package com.example.pepino.integration.api.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.pepino.api.PetApi;
import com.example.pepino.api.data.PetDTO;
import com.example.pepino.config.RestTemplateConfTest;
import com.example.pepino.model.Specie;

@Lazy // required due to @LocalServerPort
@Service
@Import(RestTemplateConfTest.class)
public class PetApiClient extends ApiClient {

    @Autowired
    @Qualifier("itRestTemplate")
    private RestTemplate restTemplate;

    @Override
    public String getEndpoint() {
        return super.getEndpoint() + PetApi.URI;
    }

    public ResponseEntity<PetDTO> create(PetDTO dto) {
        return restTemplate.postForEntity(getEndpoint(), dto, PetDTO.class);
    }

    public ResponseEntity<Long> count() {
        return restTemplate.getForEntity(getEndpoint() + "/count", Long.class);
    }

    public ResponseEntity<Long> count(Specie specie) {
        Map<String, String> vars = new HashMap<>();
        vars.put("specie", specie.name());
        return restTemplate.getForEntity(getEndpoint() + "/count?specie={specie}", Long.class, vars);
    }
}
