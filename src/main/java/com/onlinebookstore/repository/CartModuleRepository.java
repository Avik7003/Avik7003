package com.onlinebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinebookstore.entity.BooksModule;
import com.onlinebookstore.entity.CartModule;
import com.onlinebookstore.entity.Customer;

@Repository
public interface CartModuleRepository extends JpaRepository<CartModule, Long>{

	CartModule findByCustomerAndBooksModule(Customer customer, BooksModule book);

}
