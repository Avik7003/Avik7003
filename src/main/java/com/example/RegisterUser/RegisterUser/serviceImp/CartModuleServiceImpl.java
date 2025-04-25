package com.example.RegisterUser.RegisterUser.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;
import com.example.RegisterUser.RegisterUser.entity.CartModule;
import com.example.RegisterUser.RegisterUser.entity.Customer;
import com.example.RegisterUser.RegisterUser.repo.BooksRepo;
import com.example.RegisterUser.RegisterUser.repo.CartModuleRepository;
import com.example.RegisterUser.RegisterUser.repo.CustomerRepo;
import com.example.RegisterUser.RegisterUser.userRegisterService.CartModuleService;

@Service
public class CartModuleServiceImpl implements CartModuleService {

	@Autowired
	private CustomerRepo customerRepository;

	@Autowired
	private BooksRepo booksModuleRepository;

	@Autowired
	private CartModuleRepository cartModuleRepository;
		

		@Override
		public CartModule addtoCartBook(Long customerId, Long bookId, int quantity) {

			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new RuntimeException("Customer Id not found"));

			BooksModule booksModule = booksModuleRepository.findById(bookId)
					.orElseThrow(() -> new RuntimeException("Book Id not found"));

			CartModule cartItem = cartModuleRepository.findByCustomerAndBooksModule(customer, booksModule);

			if (cartItem != null) {

				cartItem.setQuantity(cartItem.getQuantity() + quantity);

			} else {

				cartItem = new CartModule(quantity, booksModule, customer);
			}

			cartItem.setTotalPrice(cartItem.getQuantity() * booksModule.getPrice());

		
			return  cartModuleRepository.save(cartItem);

		

	}
	}

	