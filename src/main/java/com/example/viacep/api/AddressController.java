package com.example.viacep.api;

import com.example.viacep.application.FindAddressByCepUseCase;
import com.example.viacep.domain.Address;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/address")
public class AddressController {

    private final FindAddressByCepUseCase useCase;

    public AddressController(FindAddressByCepUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/{cep}")
    public Address getAddress(@PathVariable String cep) {
        return useCase.find(cep);
    }
}