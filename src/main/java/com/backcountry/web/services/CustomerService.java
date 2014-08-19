package com.backcountry.web.services;

import com.backcountry.web.model.CustomerModel;
import com.backcountry.web.services.execptions.ServiceException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

//demo automatic service validation
//@Validated
public interface CustomerService {

    public CustomerModel createCustomer(/*@Valid*/ CustomerModel model) throws ServiceException;
    public CustomerModel getCustomerById(Integer id) throws ServiceException;

}
