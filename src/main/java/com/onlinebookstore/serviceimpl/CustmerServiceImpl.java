package com.onlinebookstore.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.entity.Customer;
import com.onlinebookstore.exceptions.CustmerIdNotFoundException;
import com.onlinebookstore.repository.CustomerRepository;
import com.onlinebookstore.service.CustmerService;

@Service
public class CustmerServiceImpl implements CustmerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomerOrUpdateCustomer(Customer customer) {

		if (customer.getId() == null) {

			customerRepository.save(customer); //Base64 and streams and map
		} else {

			Optional<Customer> getById = customerRepository.findById(customer.getId());

			if (getById.isPresent()) {
				Customer existCustmer = getById.get();
				existCustmer.setName(customer.getName());
				existCustmer.setEmail(customer.getEmail());
				return customerRepository.save(existCustmer);
			} else {

				throw new RuntimeException("Custmer Id not found");
			}
		}
		return customer;
	}

	@Override
	public Customer getByCustomerId(Long id) {

		Optional<Customer> byId = customerRepository.findById(id);

		if (!byId.isPresent()) {

			throw new CustmerIdNotFoundException("Custmer Id not found");
		}
		return byId.get();
	}

}
