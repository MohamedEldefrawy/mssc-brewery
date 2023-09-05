package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") UUID uuid) {
        return ResponseEntity.ok(this.customerService.getById(uuid));
    }

    @PostMapping
    ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(this.customerService.create(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    ResponseEntity<Void> updateCustomer(@PathVariable UUID customerId, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomer = this.customerService.update(customerId, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        CustomerDto deletedCustomer = this.customerService.deleteById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
