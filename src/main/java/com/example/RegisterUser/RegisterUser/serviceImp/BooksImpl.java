package com.example.RegisterUser.RegisterUser.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;
import com.example.RegisterUser.RegisterUser.repo.BooksRepo;
import com.example.RegisterUser.RegisterUser.userRegisterService.BooksModelService;
//import com.example.zomato_user.zomato_user.entity.Customer;

@Service
public class BooksImpl implements BooksModelService {

	@Autowired
	private BooksRepo booksrepo;
	@Override
	public BooksModule createdOrUpdatebook(BooksModule booksModule) {
		/*BooksModule module = booksrepo.save(booksModule);
		return module;*/
		if(booksModule.getId()== null)
		{
			booksrepo.save(booksModule);
		}
		else
		{
			Optional<BooksModule> getById = booksrepo.findById(booksModule.getId());
			if(getById.isPresent())
			{
				BooksModule existCustomer=getById.get();
				existCustomer.setTitle(booksModule.getTitle());
				existCustomer.setPrice(booksModule.getPrice());
				return booksrepo.save(existCustomer);
			}
			else
			{
				throw new RuntimeException("Custmer Id not found");
			}
		}
		return booksModule;
	}
	@Override
	public BooksModule booksFindById(Long id) {
		
			Optional<BooksModule> byId = booksrepo.findById(id);
			if(!byId.isPresent())
			{
				throw new RuntimeException("id not found");
			}
			return byId.get() ;
			
		
	}
	@Override
	public List<BooksModule> getByAllBookss() {
		
		List<BooksModule> all = booksrepo.findAll();
		return all;
	}

}
