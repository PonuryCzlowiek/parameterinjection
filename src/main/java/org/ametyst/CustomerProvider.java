package org.ametyst;

import org.springframework.stereotype.Service;

@Service
public class CustomerProvider {
    public Customer getCustomerById(Integer id) {
        return new Customer(id, "Customer number " + id);
    }
}
