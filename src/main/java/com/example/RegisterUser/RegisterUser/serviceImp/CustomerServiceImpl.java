package com.example.RegisterUser.RegisterUser.serviceImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegisterUser.RegisterUser.entity.Customer;
import com.example.RegisterUser.RegisterUser.repo.CustomerRepo;
import com.example.RegisterUser.RegisterUser.userRegisterService.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo CustomerRepo;

	

	@Override
	public Customer addCustomerOrUploadCustomer(Customer customer) {
		if(customer.getId()== null)
		{
			 CustomerRepo.save(customer);
		}
		else
		{
			Optional<Customer> getById = CustomerRepo.findById(customer.getId());
			if(getById.isPresent())
			{
				Customer existCustomer=getById.get();
				existCustomer.setName(customer.getName());
				existCustomer.setEmail(customer.getEmail());
				return CustomerRepo.save(existCustomer);
			}
			else
			{
				throw new RuntimeException("Custmer Id not found");
			}
		}
		return customer;
	}



	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> byId = CustomerRepo.findById(id);
		if(!byId.isPresent())
		{
			throw new RuntimeException("cutomer id not found");
		}
		return null;
	}



	
}
