package com.onlinebookstore.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.entity.Orders;
import com.onlinebookstore.entity.UserRegister;
import com.onlinebookstore.model.OrdersModuleDto;
import com.onlinebookstore.repository.OrderModuleRepository;
import com.onlinebookstore.repository.UserRegisterRepository;
import com.onlinebookstore.service.OrderModuleService;

@Service
public class OrderModuleServiceImpl implements OrderModuleService {

	@Autowired
	private OrderModuleRepository orderModuleRepository;

	@Autowired
	private UserRegisterRepository userRegisterRepository;

	@Override
	public String placeOrders(OrdersModuleDto ordersModuleDto) {
		    // Check if no books are selected
		    if (ordersModuleDto == null || ordersModuleDto.getTitle() == null || ordersModuleDto.getTitle().isEmpty()) {
		        return "No books selected. Please select at least one book to proceed.";
		    }

		    Long customerId = ordersModuleDto.getCustmerId();
		    List<String> selectedTitles = ordersModuleDto.getTitle();

		    // Check if user is Prime
		    boolean isPrimeUser = checkIfPrimeUser(customerId);

		    // Handle restrictions for Non-Prime Users
		    if (!isPrimeUser) {
		        // Non-prime users can order only one book at a time
		        if (selectedTitles.size() > 1) {
		            return "Non-prime users can order only one book at a time.";
		        }

		        // Check if the non-prime user has already ordered in the last week
		        List<Orders> ordersInLastWeek = orderModuleRepository.findByOrdersPlacedlastweek(customerId);
		        if (!ordersInLastWeek.isEmpty()) {
		            return "Non-prime users can order only one book per week.";
		        }
		    }

		    // Process each selected book
		    for (String title : selectedTitles) {
		        // Find book by title
		        BooksModule book = orderModuleRepository.findByName(title);
		        if (book == null) {
		            return "Book not found for title: " + title;
		        }

		        // Create and save the order
		        Orders newOrder = new Orders();
		        newOrder.setBookId(book.getId());
		        newOrder.setCustmerId(customerId);
		        newOrder.setStatus(false);  // Assuming the order is pending
		        orderModuleRepository.save(newOrder);
		    }

		    // Successful order placement
		    return "Order placed successfully. Thank you!";
		}

		private boolean checkIfPrimeUser(Long customerId) {
		    // Find the user by customerId
		    Optional<UserRegister> userOptional = userRegisterRepository.findById(customerId);

		    // Return true if the user exists and is a prime user
		    return userOptional.map(UserRegister::isPrime).orElse(false);
		}

		@Override
		public String returnOrders(OrdersModuleDto ordersModuleDto) {
			
			BooksModule bookId = orderModuleRepository.findByName(ordersModuleDto.getTitle().get(0));
			
			Orders byBookIdandCustmerId = orderModuleRepository.findByBookIdandCustmerId(bookId.getId(),ordersModuleDto.getCustmerId());
			byBookIdandCustmerId.setStatus(true);
			
			return "order return successfully Thank you..";
		}
		
}
