package org.ametyst;

import org.ametyst.inject.PathCustomer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerResource {
    @GetMapping("/customer/{customerId}")
    public String getCustomerName(@PathCustomer Customer customer) {
        return customer.getName();
    }

    @GetMapping("/user/{userId}")
    public String getCustomerNameWithNotDefaultPathParam(@PathCustomer("userId") Customer customer) {
        return customer.getName();
    }
}
