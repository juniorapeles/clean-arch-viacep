package com.example.viacep.infrastructure;

import com.example.viacep.application.FindAddressByCepUseCase;
import com.example.viacep.domain.Address;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ViaCepClient implements FindAddressByCepUseCase {

    private final RestTemplate restTemplate = new RestTemplate();
    private final WebClient webClient;

    public ViaCepClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://viacep.com.br/ws/")  // URL corrigida
                .build();
    }

    @Override
    public Address find(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, Address.class);
    }

    @Override
    public Address findWithWebClient(String cep) {
        return webClient.get()
                .uri("{cep}/json/", cep)
                .retrieve()
                .onStatus(x -> x.isError(),
                        t -> {
                            throw new RuntimeException("Cep not found: " + cep);
                        })
                .bodyToMono(Address.class)
                .block();
    }
}