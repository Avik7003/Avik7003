package com.example.RegisterUser.RegisterUser.userRegisterService;

import com.example.RegisterUser.RegisterUser.entity.Customer;

public interface CustomerService {

	Customer addCustomerOrUploadCustomer(Customer customer);

	Customer getCustomerById(Long id);

}
