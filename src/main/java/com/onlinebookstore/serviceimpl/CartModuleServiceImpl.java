package com.onlinebookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.entity.CartModule;
import com.onlinebookstore.entity.Customer;
import com.onlinebookstore.exceptions.BookIdNotFoundException;
import com.onlinebookstore.exceptions.CustmerIdNotFoundException;
import com.onlinebookstore.repository.BooksModuleRepository;
import com.onlinebookstore.repository.CartModuleRepository;
import com.onlinebookstore.repository.CustomerRepository;
import com.onlinebookstore.service.CartModuleService;

@Service
public class CartModuleServiceImpl implements CartModuleService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BooksModuleRepository booksModuleRepository;

	@Autowired
	private CartModuleRepository cartModuleRepository;

	@Override
	public CartModule addtoCartBook(Long custmerId, Long bookId, int quantity) {

		Customer customer = customerRepository.findById(custmerId)
				.orElseThrow(() -> new CustmerIdNotFoundException("Custmer Id not found"));

		BooksModule booksModule = booksModuleRepository.findById(bookId)
				.orElseThrow(() -> new BookIdNotFoundException("Book Id not found"));

		CartModule cartItem = cartModuleRepository.findByCustomerAndBooksModule(customer, booksModule);

		if (cartItem != null) {

			cartItem.setQuantity(cartItem.getQuantity() + quantity);

		} else {

			cartItem = new CartModule(quantity, booksModule, customer);
		}

		cartItem.setTotalPrice(cartItem.getQuantity() * booksModule.getPrice());

	
		return  cartModuleRepository.save(cartItem);

	}

	@Override
	public void deletecartfrombook(Long cartid) {
	
		cartModuleRepository.deleteById(cartid);
		
	}

}
