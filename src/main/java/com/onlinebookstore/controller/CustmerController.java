package com.onlinebookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebookstore.entity.Customer;
import com.onlinebookstore.repository.CustomerRepository;
import com.onlinebookstore.service.CustmerService;

@RestController
@RequestMapping("/rest")
public class CustmerController {

	@Autowired
	private CustmerService custmerService;

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping("/createOrUpdate")
	public ResponseEntity<Customer> createOrUpdated(@RequestBody Customer customer) {

		// Id equal to null means based id check database id is exist ot not if not
		// exist

		if (customer.getId() == null) {

			Customer customerOrUpdateCustomer = custmerService.addCustomerOrUpdateCustomer(customer);

			return ResponseEntity.status(HttpStatus.CREATED).body(customerOrUpdateCustomer);

			// Already exist id update the Data
		} else {

			Customer customerOrUpdateCustomer = custmerService.addCustomerOrUpdateCustomer(customer);

			return ResponseEntity.ok(customerOrUpdateCustomer);
		}

		// 15 endueser or custmer 5 or 4 fileds Model class return 4 fields only

	}

	@GetMapping("/custmer/{id}")
	public ResponseEntity<Customer> getByCusId(@PathVariable Long id) {

		Customer byCustomerId = custmerService.getByCustomerId(id);

		return ResponseEntity.status(HttpStatus.CREATED).body(byCustomerId);

	}

	@GetMapping("/getAllcustmer")
	public List<Customer> getAllCustomers() {

		List<Customer> list = customerRepository.findAll();

		return list;

	}

	@DeleteMapping("/deletecustmer/{id}")
	public void deleteCustmots(@PathVariable Long id) {

		customerRepository.deleteById(id);

	}

}
