package com.example.RegisterUser.RegisterUser.userRegisterService;

import java.util.List;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;

public interface BooksModelService {

	BooksModule createdOrUpdatebook(BooksModule booksModule);

	BooksModule booksFindById(Long id);

	List<BooksModule> getByAllBookss();

}
