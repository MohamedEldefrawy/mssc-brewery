package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getById(UUID id) {
        return CustomerDto.builder().id(UUID.randomUUID()).name("Mo").build();
    }

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        log.debug("Creating....");
        return CustomerDto.builder().id(UUID.randomUUID()).name(customerDto.getName()).build();
    }

    @Override
    public CustomerDto update(UUID customerId, CustomerDto customerDto) {
        log.debug("Updating....");
        return CustomerDto.builder().id(customerId).name(customerDto.getName()).build();
    }

    @Override
    public CustomerDto deleteById(UUID customerId) {
        log.debug("Deleting....");
        return CustomerDto.builder().id(customerId).name("Mo").build();
    }
}
