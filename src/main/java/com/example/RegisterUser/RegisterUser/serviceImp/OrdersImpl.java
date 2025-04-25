package com.example.RegisterUser.RegisterUser.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;
import com.example.RegisterUser.RegisterUser.entity.Orders;
import com.example.RegisterUser.RegisterUser.entity.Register;
import com.example.RegisterUser.RegisterUser.modal.OrderModuleDTO;
import com.example.RegisterUser.RegisterUser.repo.OrderModuleRepo;
import com.example.RegisterUser.RegisterUser.repo.userRepository;
import com.example.RegisterUser.RegisterUser.userRegisterService.OrderModuleService;

@Service
public class OrdersImpl implements OrderModuleService {

	@Autowired
	private OrderModuleRepo OrderModuleRepo;
	@Autowired
	private userRepository userRepository;
	
	@Override
	public String placeOrders(OrderModuleDTO orderModuleDTO) {
		if(orderModuleDTO==null||orderModuleDTO.getTitle()==null||orderModuleDTO.getTitle().isEmpty())
		{
			return "No books are slected, please select atleast one book to proceed";
		}
		Long customerId = orderModuleDTO.getCustomerId();
		List<String> selectedtitle = orderModuleDTO.getTitle();
		
		boolean isPrimeUser=chechIfPrimeUser(customerId);
		if(!isPrimeUser) 
		{
			if(selectedtitle.size()>1)
			{
				return "Non prime Users can order only one book";
			}
			
			List<Orders> bookOrderLastweek = OrderModuleRepo.bookOrderLastweek(customerId);
			if(!bookOrderLastweek.isEmpty())
			{
				return "Non-prime users can order only one book per week.";
			}
		}
	
		
			
		
		
		
		for(String title:selectedtitle)
		{
			BooksModule book=OrderModuleRepo.findByName(title);
			if(book==null)
			{
				return "book not found for title : " +title;
			}
			Orders ord = new Orders();
			ord.setBookId(book.getId());
			ord.setCustomerId(customerId);
			ord.setBookName(book.getTitle());
			ord.setStatus(ord.getStatus());
			ord.setPrice(book.getPrice());
			OrderModuleRepo.save(ord);
		}
		return "order placed successfully thank-you";
	}

	private boolean chechIfPrimeUser(Long customerId) {
		Optional<Register> byId = userRepository.findById(customerId);
		return byId.map(Register::isPrime).orElse(false);
	}

	

	

}
