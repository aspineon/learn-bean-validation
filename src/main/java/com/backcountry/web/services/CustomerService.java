package com.backcountry.web.services;

import com.backcountry.web.model.CustomerModel;
import com.backcountry.web.services.execptions.ServiceException;

/**
 * @author Edwin Dalorzo.
 */
public interface CustomerService {

    public CustomerModel createCustomer(CustomerModel model) throws ServiceException;
    public CustomerModel getCustomerById(Integer id) throws ServiceException;

}
