package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getById(UUID id);

    CustomerDto create(CustomerDto customerDto);

    CustomerDto update(UUID customerId, CustomerDto customerDto);

    CustomerDto deleteById(UUID customerId);
}
