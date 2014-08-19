package com.backcountry.web.controllers;

import com.backcountry.web.model.CustomerModel;
import com.backcountry.web.services.CustomerService;
import com.backcountry.web.services.execptions.ServiceException;
import com.backcountry.web.services.execptions.ValidationServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Edwin Dalorzo.
 */
@RestController
public class CustomerController extends BaseRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(
            value = "/customers/{id}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    public CustomerModel getCustomerById(@PathVariable Integer id) throws ServiceException {
        return customerService.getCustomerById(id);
    }


    @RequestMapping(
            value = "/customers",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public CustomerModel createCustomer(@RequestBody @Validated CustomerModel model, BindingResult result) throws ServiceException {
        if(result.hasErrors()){
            throw new ValidationServiceException(result);
        }
        return customerService.createCustomer(model);
    }

}
