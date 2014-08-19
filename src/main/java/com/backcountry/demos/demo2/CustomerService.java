package com.backcountry.demos.demo2;


import com.backcountry.demos.Utils;
import com.backcountry.demos.demo2.model.Customer;

import javax.validation.*;
import java.util.Set;

import static com.backcountry.demos.Utils.date;

public class CustomerService {

    private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private Validator validator = vf.getValidator();

    public Customer createCustomer(Customer customer) {

        Set<ConstraintViolation<Customer>> violations;
        violations = validator.validate(customer);
        if (violations.size() > 0) {
            Utils.printErrors(violations);
        }
        return customer;
    }

    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setFirstName("Jules");
        customer.setLastName("Verne");
        customer.setEmail("jules@verne");
        customer.setPhoneNumber("8875-0372");
        customer.setDateOfBirth(date("1828-02-08"));

        CustomerService service = new CustomerService();
        service.createCustomer(customer);
    }

}
