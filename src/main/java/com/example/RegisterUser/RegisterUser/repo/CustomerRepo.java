package com.example.RegisterUser.RegisterUser.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RegisterUser.RegisterUser.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

}
