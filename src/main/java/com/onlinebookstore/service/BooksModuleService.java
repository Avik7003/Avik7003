package com.onlinebookstore.service;

import java.util.List;

import com.onlinebookstore.entity.BooksModule;

public interface BooksModuleService {

	public BooksModule createdOrUpdatebook(BooksModule booksModule);

	public BooksModule getBookId(Long bookId);

	public List<BooksModule>  getByAllBookss();

}
