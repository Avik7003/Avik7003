package com.example.RegisterUser.RegisterUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterUser.RegisterUser.entity.Customer;
//import com.example.zomato_user.zomato_user.userRegisterService.CustomerService;
import com.example.RegisterUser.RegisterUser.userRegisterService.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addcustomer")
	public ResponseEntity<Customer> addCustomerOrUpload(@RequestBody Customer customer)
	{
		if(customer.getId()==null)
		{
			Customer existcustomer=customerService.addCustomerOrUploadCustomer(customer);
			return ResponseEntity.ok(existcustomer);
		}
		else
		{
			Customer existcustomer=customerService.addCustomerOrUploadCustomer(customer);
			return ResponseEntity.ok(existcustomer);
		}
		
		
	}
	@GetMapping("/getcustomer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id)
	{
		Customer existingCus = customerService.getCustomerById(id);
		return null;
		
	}
}
