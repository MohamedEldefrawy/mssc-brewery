package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("{id}")
  ResponseEntity<CustomerDto> getCustomerById(@NotNull @PathVariable("id") UUID uuid) {
    return ResponseEntity.ok(this.customerService.getById(uuid));
  }

  @PostMapping
  ResponseEntity<CustomerDto> createCustomer(@NotNull @RequestBody CustomerDto customerDto) {
    return new ResponseEntity<>(this.customerService.create(customerDto), HttpStatus.CREATED);
  }

  @PutMapping("{customerId}")
  ResponseEntity<String> updateCustomer(@NotNull @PathVariable UUID customerId, @NotNull @RequestBody CustomerDto customerDto) {
    CustomerDto updatedCustomer = this.customerService.update(customerId, customerDto);
    if (updatedCustomer != null) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
    return new ResponseEntity<>("Couldn't update customer with ID: " + customerId.toString(), HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping("{customerId}")
  ResponseEntity<String> deleteCustomer(@PathVariable UUID customerId) {
    CustomerDto deletedCustomer = this.customerService.deleteById(customerId);
    if (deletedCustomer != null) {return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
    return new ResponseEntity<>("Couldn't delete customer with ID: " + customerId.toString(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  ResponseEntity<List<String>> validationExceptionHandler(ConstraintViolationException e) {
    List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

    e.getConstraintViolations().forEach(x -> errors.add(x.getPropertyPath() + ": " + x.getMessage()));

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
