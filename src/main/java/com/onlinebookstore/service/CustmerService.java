package com.onlinebookstore.service;

import com.onlinebookstore.entity.Customer;

public interface CustmerService {

	public Customer addCustomerOrUpdateCustomer(Customer customer);

	public Customer getByCustomerId(Long id);

}
