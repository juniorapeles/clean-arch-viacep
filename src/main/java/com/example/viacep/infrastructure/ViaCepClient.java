package com.example.viacep.infrastructure;

import com.example.viacep.application.FindAddressByCepUseCase;
import com.example.viacep.domain.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepClient implements FindAddressByCepUseCase {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Address find(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, Address.class);
    }
}