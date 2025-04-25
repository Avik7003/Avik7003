package com.onlinebookstore.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.exceptions.BookIdNotFoundException;
import com.onlinebookstore.repository.BooksModuleRepository;
import com.onlinebookstore.service.BooksModuleService;

@Service
public class BooksModuleServiceImpl implements BooksModuleService {

	@Autowired
	private BooksModuleRepository booksModuleRepository;

	@Override
	public BooksModule createdOrUpdatebook(BooksModule booksModule) {

		BooksModule bookmod = booksModuleRepository.save(booksModule);

		return bookmod;

	}

	@Override
	@Cacheable(cacheNames = "booksmodule" ,key ="#bookId")
	public BooksModule getBookId(Long bookId) {
		System.err.println("getbyBookId  calls from database ...............................");
		Optional<BooksModule> bId = booksModuleRepository.findById(bookId);

		if (!bId.isPresent()) {

			throw new BookIdNotFoundException("Book id notfound");

		}

		return bId.get();
	}

	@Override
	  @Cacheable(value = "allbooks")
	public List<BooksModule>  getByAllBookss() {
		System.err.println("getbyAll books calls from database ...............................");
		List<BooksModule> list = booksModuleRepository.findAll();
		
		return list;
		
	}

}
