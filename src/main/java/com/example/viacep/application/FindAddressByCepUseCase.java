package com.example.viacep.application;

import com.example.viacep.domain.Address;

public interface FindAddressByCepUseCase {
    Address find(String cep);
    Address findWithWebClient(String cep);
}