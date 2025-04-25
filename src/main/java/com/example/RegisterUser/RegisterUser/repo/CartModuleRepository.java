package com.example.RegisterUser.RegisterUser.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RegisterUser.RegisterUser.entity.BooksModule;
import com.example.RegisterUser.RegisterUser.entity.CartModule;
import com.example.RegisterUser.RegisterUser.entity.Customer;

@Repository
public interface CartModuleRepository extends JpaRepository<CartModule, Long>{

	CartModule findByCustomerAndBooksModule(Customer customer, BooksModule booksModule);

}
