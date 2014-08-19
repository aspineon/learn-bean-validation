package com.backcountry.web.services;

import com.backcountry.web.domain.Customer;
import com.backcountry.web.model.CustomerModel;
import com.backcountry.web.repositories.CustomerRepository;
import com.backcountry.web.services.execptions.InternalServerErrorServiceException;
import com.backcountry.web.services.execptions.ServiceException;
import com.backcountry.web.services.execptions.ValidationServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @author Edwin Dalorzo.
 */
@Service
public class DefaultCustomerService implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Validator validator;

    @Override
    public CustomerModel createCustomer(CustomerModel model) throws ServiceException {
        Set<ConstraintViolation<CustomerModel>> violations = validator.validate(model);
        if (violations.size() > 0) {
            throw new ValidationServiceException(violations);
        }
        try {
            return map(customerRepository.save(map(model)));
        } catch (Exception e) {
            throw new InternalServerErrorServiceException(e.getMessage());
        }
    }

    @Override
    public CustomerModel getCustomerById(Integer id) throws ServiceException {
        return map(customerRepository.findOne(id));
    }

    private CustomerModel map(Customer customer) {
        CustomerModel model = new CustomerModel();
        model.setFirstName(customer.getFirstName());
        model.setLastName(customer.getLastName());
        model.setEmail(customer.getEmail());
        model.setDateOfBirth(customer.getDateOfBirth());
        return model;
    }

    private Customer map(CustomerModel model) {
        Customer customer = new Customer();
        customer.setFirstName(model.getFirstName());
        customer.setLastName(model.getLastName());
        customer.setEmail(model.getEmail());
        customer.setDateOfBirth(model.getDateOfBirth());
        return customer;
    }
}
